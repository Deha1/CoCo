package com.coco.controller;


import com.coco.commom.ServerResponse;
import com.coco.pojo.Shop;
import com.coco.service.Discount;
import com.coco.service.GoodsService;
import com.coco.service.Search;
import com.coco.service.imp.SearchServiceimp;
import com.coco.commom.vo.ItemPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private Search search;
    @Autowired
    private SearchServiceimp searchServiceimp;
    @Autowired
    private Discount discount;

    @RequestMapping("/kind")
    @ResponseBody
    public ServerResponse selectBykind(Integer kindid){
        System.out.println("******ok  kind*****");
        List<ItemPage> goods = goodsService.selectBykind(kindid);
        for (ItemPage a: goods
             ) {
            System.out.println(a.getGoods_name()+"******"+a.getShop_name()+"******");
        }


        return ServerResponse.createBySuccess(goods);
    }
    @RequestMapping("/search")
    @ResponseBody
    public ServerResponse selectByname(String goodsName){
//        Goods goods=new Goods();
//        goods.setName("茶");
//        try {
//            goodsName = new String(goodsName.getBytes("iso8859-1"), "utf-8");
//
//        }catch (Exception e){
//
//        }
        System.out.println(goodsName+"**********");
            List<ItemPage> goods=search.selectByname(goodsName);
//        System.out.println(goods);

        return ServerResponse.createBySuccess(goods);
    }
    @RequestMapping("/shop")
    @ResponseBody
    public ServerResponse selectShopByname(String shopname){
        System.out.println("OK"+"***************");
        System.out.println(shopname+"////////////\\\\\\\\\\\\\\\"");
        List<Shop> shop= search.selectByshop(shopname);
        return ServerResponse.createBySuccess(shop);
    }
    @RequestMapping("/sell")
    @ResponseBody
    public ServerResponse youhui(){

        return ServerResponse.createBySuccess(discount.selectByid());
    }


}
