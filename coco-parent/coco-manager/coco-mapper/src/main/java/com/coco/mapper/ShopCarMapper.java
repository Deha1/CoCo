package com.coco.mapper;

import com.coco.pojo.Shop;
import com.coco.pojo.ShopCar;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopCarMapper {
    int insert(ShopCar record);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(ShopCar record);




    List<ShopCar> selectCheckedCartByUserId(Integer userId);

    public ShopCar findaShopCar(@Param("userId") int userId, @Param("shopId") int shopId, @Param("goodsId") int goodsId);

    public void addShopCar(ShopCar shopCar);

    public List<ShopCar> findShopCarGoods();

    public void updataShopCar(ShopCar shopCar);

    public void deleteShopCarById(int id);

    public List<ShopCar> personShopCar(int user_id);

    //批量删除购物车
    public void deleteAllShopCarByUserId(int userId);

}