package com.coco.service.imp;

import com.coco.common.ServerResponse;
import com.coco.mapper.GoodsMapper;
import com.coco.pojo.Goods;
import com.coco.pojo.GoodsPic;
import com.coco.service.SelectService;
import com.coco.vo.GoodsDetails;
import com.coco.vo.GoodsPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SelectServiceImp implements SelectService {

    @Autowired
    private GoodsMapper gm;

    @Override
    public Goods getGoods(Goods goods) {
        //查到goods所有字段
        Goods my_goods=gm.selectGoods(goods.getId());

        return my_goods;
    }

    public ServerResponse findGoodsDetails(Goods goods){
        Goods my_goods = this.getGoods(goods);
        GoodsDetails goodsDetails=this.goodsToGoodsDetails(my_goods);

        return ServerResponse.createBySuccess(goodsDetails);
    }

    //将goods封装成GoodsPageVo
    public GoodsPage goodsToGoodsPageVo(Goods my_goods){

        GoodsPage goodsPage = new GoodsPage();

        List<GoodsPic> pic=my_goods.getGoodsPic();

        goodsPage.setGoods_pic(pic.get(0).getUrl());
        goodsPage.setGoods_id(my_goods.getId());
        goodsPage.setShop_id(my_goods.getShopId());
        goodsPage.setGoods_name(my_goods.getName());
        goodsPage.setGoods_price(my_goods.getPrice());
        goodsPage.setTop_show(my_goods.getTopShow());
        goodsPage.setShop_name(my_goods.getShop().getShopName());

        return goodsPage;
    }

    //将goods数据封装到GoodsDetails
    public GoodsDetails goodsToGoodsDetails(Goods my_goods){

        GoodsDetails goodsDetails = new GoodsDetails();

        List<GoodsPic> pic=new ArrayList();
        List<String> pic_url=new ArrayList();
        pic=my_goods.getGoodsPic();
        for(int cont=0;cont<pic.size();cont++){
            pic_url.add(pic.get(cont).getUrl());
        }
        goodsDetails.setPic_url(pic_url);

        if(my_goods.getStatus()!=0){
            if(my_goods.getStatus()==1){
                goodsDetails.setStatus("在售");
            }
            goodsDetails.setStatus("下线");
        }

        goodsDetails.setGoods_id(my_goods.getId());
        goodsDetails.setKind_id(my_goods.getKindId());
        goodsDetails.setShop_id(my_goods.getShopId());
        goodsDetails.setName(my_goods.getName());
        goodsDetails.setDetail(my_goods.getDetail());
        goodsDetails.setNum(my_goods.getNum());
        goodsDetails.setSubtitle(my_goods.getSubtitle());
        goodsDetails.setPrice(my_goods.getPrice());
        goodsDetails.setTop_show(my_goods.getTopShow());
        goodsDetails.setShop_name(my_goods.getShop().getShopName());
        goodsDetails.setKind_name(my_goods.getGoodsKind().getName());


        return goodsDetails;
    }


}
