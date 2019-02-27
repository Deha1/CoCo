package com.coco.mapper;

import com.coco.pojo.Shop;

import java.util.List;

public interface ShopMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shop record);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);
    //获取商店列表
    List<Shop> selectshopByname(String shopname);
    //获得商店名
    String selectByshopid(Integer shopid);

    //展示特定商店
    List<Shop> selectByid();

    public List<Shop> findAllShop();

}