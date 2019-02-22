package com.coco.service.imp;

import com.coco.mapper.GoodsMapper;
import com.coco.pojo.Goods;
import com.coco.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceimp implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;


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
