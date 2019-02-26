package com.coco.service.imp;

import com.coco.commom.Const;
import com.coco.commom.util.BigDecimalUtil;
import com.coco.commom.vo.AddressVo;
import com.coco.commom.vo.OrdersInfoVo;
import com.coco.commom.vo.OrdersVo;
import com.coco.mapper.*;
import com.coco.pojo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.coco.commom.ServerResponse;
import com.coco.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service("ordersService")
public class OrdersServiceImp implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private ShopCarMapper shopCarMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsPicMapper goodsPicMapper;
    @Autowired
    private OrdersInfoMapper ordersInfoMapper;
    @Autowired
    private AddressMapper addressMapper;


    @Override

    //创建订单
    public ServerResponse createOrder(Integer userId){
        //从购物车中获取数据
        List<ShopCar> shopCarList = shopCarMapper.selectCheckedCartByUserId(userId);

        //计算这个订单的总价
        ServerResponse serverResponse = this.getCartOrderItem(userId,shopCarList);
        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }
        List<OrdersInfo> orderInfoList = (List<OrdersInfo>) serverResponse.getData();
        BigDecimal payment = this.getOrderTotalPrice(orderInfoList);

        Address address = addressMapper.selectByPrimaryKey(userId);
        //生成订单
        Orders orders = this.assembleOrder(userId,address.getId(),payment);
        if (orders == null) {
            return ServerResponse.createByErrorMessage("生成订单错误");
        }
        if (CollectionUtils.isEmpty(orderInfoList)) {
            return ServerResponse.createByErrorMessage("购物车为空");
        }
        for (OrdersInfo ordersInfo : orderInfoList) {
            ordersInfo.setOrderNo(orders.getOrderNo());
        }
        //mybatis 批量插入
        ordersInfoMapper.batchInsert(orderInfoList);

        //生成成功,我们要减少我们产品的库存
        this.reduceProductStock(orderInfoList);
        //清空一下购物车
        this.cleanCart(shopCarList);

        //返回给前端数据

        OrdersVo ordersVo = assembleOrderVo(orders,orderInfoList);
        return ServerResponse.createBySuccess(ordersVo);
    }

    //取消订单
    public ServerResponse<String> cancel(Integer userId,Long orderNo){

        Orders orders  = ordersMapper.selectByUserIdAndOrderNo(userId,orderNo);

        if(orders == null){
            return ServerResponse.createByErrorMessage("该用户此订单不存在");
        }
        if(orders.getStatus() != Const.OrderStatusEnum.NO_PAY.getCode()){
            return ServerResponse.createByErrorMessage("已付款,无法取消订单");
        }
        Orders updateOrder = new Orders();
        updateOrder.setId(orders.getId());
        updateOrder.setStatus(Const.OrderStatusEnum.CANCELED.getCode());

        int row = ordersMapper.updateByPrimaryKeySelective(updateOrder);
        if(row > 0){
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }

    //列出订单列表
    public ServerResponse<PageInfo> getOrderList(Integer userId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);

        List<Orders> ordersList = ordersMapper.selectByUserId(userId);

        List<OrdersVo> ordersVoList = assembleOrderVoList(ordersList,userId);
        PageInfo pageResult = new PageInfo(ordersList);
        pageResult.setList(ordersVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    private List<OrdersVo> assembleOrderVoList(List<Orders> orderList,Integer userId){
        List<OrdersVo> orderVoList = Lists.newArrayList();
        for(Orders orders : orderList){
            List<OrdersInfo>  orderItemList = Lists.newArrayList();
            if(userId == null){
                //todo 管理员查询的时候 不需要传userId
                orderItemList = ordersInfoMapper.getByOrderNo(orders.getOrderNo());
            }else{
                orderItemList = ordersInfoMapper.getByOrderNoUserId(orders.getOrderNo(),userId);
            }
            OrdersVo ordersVo = assembleOrderVo(orders,orderItemList);
            orderVoList.add(ordersVo);
        }
        return orderVoList;
    }

    //查看订单详情

    public ServerResponse<OrdersVo> getOrderDetail(Integer userId,Long orderNo){
        Orders orders = ordersMapper.selectByUserIdAndOrderNo(userId,orderNo);
        if(orders != null){
            List<OrdersInfo> ordersInfoList = ordersInfoMapper.getByOrderNoUserId(orderNo,userId);
            OrdersVo ordersVo = assembleOrderVo(orders,ordersInfoList);
            return ServerResponse.createBySuccess(ordersVo);
        }
        return  ServerResponse.createByErrorMessage("没有找到该订单");
    }


    //检查购物车中商品，看是否可以提交订单，如果能先操作OrderInfo表再返回
    private ServerResponse getCartOrderItem(Integer userId, List<ShopCar> shopCarList) {
        List<OrdersInfo> ordersInfoList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(shopCarList)) {
            return ServerResponse.createByErrorMessage("购物车为空");
        }
        //校验购物车的数据,包括产品的状态和数量
        for(ShopCar shopCar : shopCarList){
            OrdersInfo ordersInfo = new OrdersInfo();
            Goods goods = goodsMapper.selectByPrimaryKey(shopCar.getGoodsId());
            if(Const.ProductStatusEnum.ON_SALE.getCode()!=goods.getStatus()){
                return ServerResponse.createByErrorMessage("产品"+goods.getName()+"不是在线售卖状态");
            }

            //校验库存
            if(shopCar.getNum() > goods.getNum()){
                return ServerResponse.createByErrorMessage("产品"+goods.getName()+"库存不足");
            }

            String str=goodsPicMapper.findPic(goods.getId());

            ordersInfo.setUserId(userId);
            ordersInfo.setGoodsId(goods.getId());
            ordersInfo.setGoodsName(goods.getName());
            ordersInfo.setGoodsPic(str);
            ordersInfo.setTotalPrice(goods.getPrice());
            ordersInfo.setGoodsNum(shopCar.getNum());
            ordersInfo.setTotalPrice(BigDecimalUtil.mul(goods.getPrice().doubleValue(),shopCar.getNum()));
            ordersInfoList.add(ordersInfo);
        }
        return ServerResponse.createBySuccess(ordersInfoList);
    }

    //计算商品总价
    private BigDecimal getOrderTotalPrice(List<OrdersInfo> orderItemList){
        BigDecimal payment = new BigDecimal("0");
        for(OrdersInfo orderItem : orderItemList){
            payment = BigDecimalUtil.add(payment.doubleValue(),orderItem.getTotalPrice().doubleValue());
        }
        return payment;
    }

    //生成订单
    private Orders assembleOrder(Integer userId,Integer adderssId,BigDecimal payment){
        Orders orders = new Orders();
        long orderNo = this.generateOrderNo();
        orders.setOrderNo(orderNo);
        orders.setStatus(Const.OrderStatusEnum.NO_PAY.getCode());
        orders.setPostage(new BigDecimal(10));
        orders.setPayType(Const.PaymentTypeEnum.ONLINE_PAY.getCode());
        orders.setPayment(payment);

        orders.setUserId(userId);
        orders.setAddressId(adderssId);

        orders.setPayTime(new Date());
        orders.setEndTime(new Date());
        orders.setSendTime(new Date());
        orders.setCreateTime(new Date());
        orders.setUpdateTime(new Date());
        int rowCount = ordersMapper.insert(orders);
        if(rowCount > 0){
           return orders;
        }
        return null;
    }

   /* //雪花算法生成订单ID
    private long generateOrderNo()throws Exception{

        SnowFlakeUtil snowFlakeUtil=SnowFlakeUtil.getInstanceSnowflake();
        return snowFlakeUtil.nextId();
    }*/

   //生成订单号
   private long generateOrderNo(){
       long currentTime =System.currentTimeMillis();
       return currentTime+new Random().nextInt(100);
   }

    //
    //

    //判断库存
    private void reduceProductStock(List<OrdersInfo> orderItemList){
        for(OrdersInfo orderItem : orderItemList){
            Goods goods = goodsMapper.selectByPrimaryKey(orderItem.getGoodsId());
            goods.setNum(goods.getNum()-orderItem.getGoodsNum());
            goodsMapper.updateByPrimaryKeySelective(goods);
        }
    }
    //清除购物车商品
    private void cleanCart(List<ShopCar> cartList){
        for(ShopCar shopCar : cartList){
            shopCarMapper.deleteByPrimaryKey(shopCar.getId());
        }
    }
    //生成订单VO
    private OrdersVo assembleOrderVo(Orders orders,List<OrdersInfo> orderItemList){
        OrdersVo ordersVo = new OrdersVo();
        ordersVo.setOrderNo(orders.getOrderNo());
        ordersVo.setPayment(orders.getPayment());
        ordersVo.setPayType(orders.getPayType());
        ordersVo.setPostage(orders.getPostage());
        ordersVo.setStatus(orders.getStatus());
        ordersVo.setAddressId(orders.getAddressId());
        Address address = addressMapper.selectByPrimaryKey(orders.getAddressId());
        if(address != null){
            ordersVo.setReceiverName(address.getReceiverUsername());
            ordersVo.setAddressVo(assembleAddressVo(address));
        }

        ordersVo.setPayTime(new Date());
        ordersVo.setSendTime(new Date());
        ordersVo.setEndTime(new Date());
        ordersVo.setCreateTime(new Date());



        ordersVo.setPic("hjkkk");


        List<OrdersInfoVo> ordersInfoVosVoList = Lists.newArrayList();

        for(OrdersInfo ordersInfo : orderItemList){
            OrdersInfoVo ordersInfoVo = assembleOrderItemVo(ordersInfo);
            ordersInfoVosVoList.add(ordersInfoVo);
        }
        ordersVo.setOrdersInfoVosList(ordersInfoVosVoList);
        return ordersVo;
    }
    //生成地址Vo
    private AddressVo assembleAddressVo(Address address){
        AddressVo addressVo = new AddressVo();
        addressVo.setReceiverUsername(address.getReceiverUsername());
        addressVo.setProvince(address.getProvince());
        addressVo.setCity(address.getCity());
        addressVo.setDistrict(address.getDistrict());
        addressVo.setDetails(address.getDetails());
        addressVo.setReceiverPhone(address.getReceiverPhone());
        addressVo.setPostcode(address.getPostcode());
        addressVo.setReceiverPhone(addressVo.getReceiverPhone());
        return addressVo;
    }
    //生成订单详细Vo
    private OrdersInfoVo assembleOrderItemVo(OrdersInfo ordersInfo){
        OrdersInfoVo ordersInfoVo = new OrdersInfoVo();
        ordersInfoVo.setOrderNo(ordersInfo.getOrderNo());
        ordersInfoVo.setGoodsId(ordersInfo.getGoodsId());
        ordersInfoVo.setGoodsName(ordersInfo.getGoodsName());
        ordersInfoVo.setGoodsPic(ordersInfo.getGoodsPic());

        ordersInfoVo.setGoodsNum(ordersInfo.getGoodsNum());
        ordersInfoVo.setTotalPrice(ordersInfo.getTotalPrice());

        ordersInfoVo.setCreateTime(ordersInfo.getCreateTime());
        return ordersInfoVo;
    }

}
