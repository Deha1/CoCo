package com.coco.service;

import com.coco.commom.vo.ItemPage;
import com.coco.pojo.Goods;

import java.util.List;

//首页分类展示
public interface GoodsService {

    List<ItemPage> selectBykind(Integer kndid);

    public List<Goods> findGoods(int shop_id);

    public Goods findshopId(int id);
}
