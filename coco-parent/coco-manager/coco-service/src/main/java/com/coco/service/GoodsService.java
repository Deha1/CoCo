package com.coco.service;

import com.coco.pojo.Goods;

import java.util.List;

public interface GoodsService {


    public List<Goods> findGoods(int shop_id);

    public Goods findshopId(int id);

}
