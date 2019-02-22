package com.coco.mapper;

import com.coco.pojo.Goods;
import java.util.List;

public interface GoodsMapper {

    public List<Goods> findGoodbyId(int shop_id);

    public Goods findshopId(int id);
}