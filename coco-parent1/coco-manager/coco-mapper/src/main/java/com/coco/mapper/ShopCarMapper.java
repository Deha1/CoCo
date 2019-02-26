package com.coco.mapper;

import com.coco.pojo.ShopCar;

import java.util.List;

public interface ShopCarMapper {
    int insert(ShopCar record);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(ShopCar record);




    List<ShopCar> selectCheckedCartByUserId(Integer userId);

}