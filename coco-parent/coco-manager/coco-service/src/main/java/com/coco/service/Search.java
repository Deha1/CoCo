package com.coco.service;


import com.coco.pojo.Shop;
import com.coco.commom.vo.ItemPage;

import java.util.List;

public interface Search {
     List<ItemPage> selectByname(String goodsName);

     List<Shop> selectByshop(String shopname);

     List<ItemPage> selectShoptoGoods(Integer shopId);
}
