package com.coco.service;

import com.coco.pojo.Goods;

import java.util.List;

public interface Search {
    public List<Goods> selectByname(String goodsName);
}
