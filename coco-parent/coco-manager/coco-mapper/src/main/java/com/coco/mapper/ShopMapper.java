package com.coco.mapper;

import com.coco.pojo.Shop;

import java.util.List;

public interface ShopMapper {
    public List<Shop> findAllShop();

    public Shop findShopbyGoodsId(int id);
}