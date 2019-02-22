package com.coco.service;

import com.coco.pojo.ShopCar;

import java.util.List;

public interface ShopCarService {
    public void addShopCarService(ShopCar shopCar);
    public List<ShopCar> findShopCar();
    public void updateShopCar(ShopCar shopCar);
    public void deleteShopCarById(int id);
    public List<ShopCar> personShopCar(int useId);
    public void deleteAllShopCarByUserId(int userId);
    public ShopCar findaShopCar(int goodsId, int shopId, int userId);

}
