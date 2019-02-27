package com.coco.service.imp;

import com.coco.mapper.GoodsKindMapper;
import com.coco.mapper.GoodsMapper;
import com.coco.mapper.GoodsPicMapper;
import com.coco.mapper.ShopMapper;
import com.coco.pojo.Goods;
import com.coco.service.GoodsService;
import com.coco.commom.vo.ItemPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceimp implements GoodsService {
    @Autowired
    private GoodsKindMapper goodsKindMapper;
    @Autowired
    private GoodsPicMapper picMapper;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<ItemPage> selectBykind(Integer kindid) {
        List<Goods> goods=goodsMapper.selectBykind(kindid);
        List<ItemPage> itemPages = new ArrayList<>();
        for (Goods good:goods
             ) {
            ItemPage itemPage=new ItemPage();
            itemPage.setGoods_id(good.getId());
            itemPage.setGoods_name(good.getName());
            itemPage.setGoods_price(good.getPrice());
            itemPage.setShop_name(shopMapper.selectByshopid(good.getShopId()));
            itemPage.setGoods_url(picMapper.selectpicByid(good.getId()));
            itemPage.setKind_name(goodsKindMapper.selectkindneam(good.getKindId()));
            System.out.println(good.getName()+"\t"+good.getSubtitle());
            itemPages.add(itemPage);
        }
        return itemPages;
    }
    @Override
    public List<Goods> findGoods(int shop_id) {
        List<Goods> list=goodsMapper.findGoodbyId(shop_id);
        return  list;
    }

    @Override
    public Goods findshopId(int id) {
        Goods good=goodsMapper.findshopId(id);
        good.getShopId();
        return good;
    }



}
