package com.coco.service.imp;

import com.coco.mapper.GoodsKindMapper;
import com.coco.mapper.GoodsMapper;
import com.coco.mapper.GoodsPicMapper;
import com.coco.mapper.ShopMapper;
import com.coco.pojo.Goods;
import com.coco.pojo.GoodsPage;

import com.coco.pojo.Shop;
import com.coco.service.Search;
import com.coco.commom.vo.ItemPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.directory.SearchResult;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceimp implements Search {
    @Autowired
    private GoodsKindMapper goodsKindMapper;
    @Autowired
    private GoodsPicMapper picMapper;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private GoodsMapper goodsMapper;


    @Override
    public List<ItemPage> selectByname(String goodsName) {
        List<Goods> goods=goodsMapper.selectByname(goodsName);
        List<ItemPage> itemPages = new ArrayList<>();
        for (Goods good:goods) {

            System.out.println(good.getName()+"\t"+good.getShopId());

            ItemPage itemPage=new ItemPage();

            itemPage.setGoods_id(good.getId());
            itemPage.setGoods_name(good.getName());
            itemPage.setGoods_price(good.getPrice());
            itemPage.setShop_id(good.getShopId());
            itemPage.setShop_name(shopMapper.selectByshopid(good.getShopId()));
            itemPage.setGoods_url(picMapper.selectpicByid(good.getId()));
            itemPage.setKind_name(goodsKindMapper.selectkindneam(good.getKindId()));
            itemPages.add(itemPage);

        }

        return itemPages;
    }

    @Override
    public List<Shop> selectByshop(String shopname) {

        List<Shop> shops=shopMapper.selectshopByname(shopname);

        return shops;
    }


    @Override
    public List<ItemPage> selectShoptoGoods(Integer shopId) {

        List<Goods> goods= goodsMapper.selectByshopid(shopId);
        return null;
    }

}
