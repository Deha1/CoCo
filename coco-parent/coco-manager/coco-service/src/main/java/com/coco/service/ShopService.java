package com.coco.service;

import com.coco.pojo.Shop;

import java.util.List;

public interface ShopService {
    public List<Shop> findShop();

    public Shop findShopByGoodId(int id);
}
