package com.coco.mapper;

import com.coco.pojo.ShopCar;

public interface ShopCarMapper {
    int insert(ShopCar record);

    int insertSelective(ShopCar record);
}