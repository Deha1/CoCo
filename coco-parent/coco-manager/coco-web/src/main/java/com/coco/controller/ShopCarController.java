package com.coco.controller;

import com.coco.common.ServerResponse;
import com.coco.pojo.Goods;
import com.coco.pojo.ShopCar;
import com.coco.pojo.User;
import com.coco.service.GoodsService;
import com.coco.service.ShopCarService;
import com.coco.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@Controller
public class ShopCarController {
    @Autowired
    ShopCarService shopCarService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    ShopService shopService;

    @ResponseBody
    @RequestMapping("/findmyshopcar.do")
    public ServerResponse myShopCar(HttpSession session){

        User user=(User) session.getAttribute("user");
        if(user==null){
            System.out.println(ServerResponse.createByErrorMessage("用户未登录"));
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        int useId=user.getId();
        List<ShopCar> shopCars=shopCarService.personShopCar(useId);
        for (ShopCar shopcar:shopCars) {
            System.out.println(shopcar.toString());
        }
        System.out.println(ServerResponse.createBySuccess(shopCars));
        return ServerResponse.createBySuccess(shopCars);
    }


    @ResponseBody
    @RequestMapping("/addmyshopcar.do")
    public ServerResponse addShopCar(@RequestParam("id") int id,HttpSession session)
    {

        Goods good=goodsService.findshopId(id);


        User user=(User)session.getAttribute("user");
        if(user==null){
            System.out.println(ServerResponse.createByErrorMessage("用户未登录"));
            return ServerResponse.createByErrorMessage("用户未登录");
        }

        int userId=user.getId();

//        //找到所有商家
//        List<Shop> shops=shopService.findShop();
//        for(Shop shop:shops){
//            System.out.println(shop.toString());
//        }
//        //System.out.println(shop.getSid());
//        System.out.println("--------------->");
//        //找到商家所属的商品
//        List<Goods> goods=goodsService.findGoods(2);
//        for(Goods goods1:goods){
//            System.out.println(goods1.toString());
//        }
//        Goods good2=goods.get(1);
//        System.out.println("------"+good2+"********");
//        System.out.println("------------->");

        //添加购物车
        ShopCar shopCar1=shopCarService.findaShopCar(id,good.getShopId(),userId);
        System.out.println("查找"+shopCar1);
        ShopCar shopCar=new ShopCar();
        //shopCar.getGoodsId();
        shopCar.setUserId(10);
        shopCar.setShopId(good.getShopId());
        shopCar.setGoodsId(id);
        shopCar.setNum(1);
        shopCar.setChecked(0);
        shopCarService.addShopCarService(shopCar);
        System.out.println(ServerResponse.createBySuccessMessage("成功加入购物车"));
        return ServerResponse.createBySuccessMessage("成功加入购物车");
    }

    @ResponseBody
    @RequestMapping("/updatemyshopcar.do")
    public Serializable updateShopCar(@RequestParam("id") int id, @RequestParam("num") int num, HttpSession session){
        User user=(User) session.getAttribute("user");
        if(user==null){
            System.out.println(ServerResponse.createByErrorMessage("用户未登录"));
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        int useId=user.getId();

        ShopCar shopCar=new ShopCar();
        shopCar.setId(id);
        shopCar.setNum(num);
        //如果商品数量为零删除这条数据
//        if(num==0){
//            shopCarService.deleteShopCarById(id);
//            return "delete";
//        }
        shopCarService.updateShopCar(shopCar);
        System.out.println(ServerResponse.createBySuccessMessage("更新成功"));
        return ServerResponse.createBySuccessMessage("更新成功");
    }
    @ResponseBody
    @RequestMapping("/deleteshopcar.do")
    public ServerResponse deleteShopCar(@RequestParam("id") int []id,HttpSession session){

        User user=(User) session.getAttribute("user");
        if(user==null){
            System.out.println(ServerResponse.createByErrorMessage("用户未登录"));
            return ServerResponse.createByErrorMessage("用户未登录");
        }
//        List list=new ArrayList();
//        List<ShopCar> shopCars=shopCarService.personShopCar(1);
//        for (ShopCar shopcar:shopCars) {
//            list.add(shopcar.getId());
//        }
//        Iterator iterator=list.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
        System.out.println(id.length);
        for(int i=0;i<id.length;i++)
            shopCarService.deleteShopCarById(id[i]);
        System.out.println(ServerResponse.createBySuccessMessage("批量删除成功"));
        return ServerResponse.createBySuccessMessage("批量删除成功");
    }

    @ResponseBody
    @RequestMapping("/deletemyshopcar.do")
    public ServerResponse DeleteAllShopCar(HttpSession session){
        User user=(User)session.getAttribute("user");
        if(user==null){
            System.out.println(ServerResponse.createByErrorMessage("用户未登录"));
            return ServerResponse.createByErrorMessage("用户未登录");
        }

        shopCarService.deleteAllShopCarByUserId(user.getId());
        System.out.println(ServerResponse.createBySuccessMessage("删除成功"));
        return ServerResponse.createBySuccessMessage("删除成功");
    }}
