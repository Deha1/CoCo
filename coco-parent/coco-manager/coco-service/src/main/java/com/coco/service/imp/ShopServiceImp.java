package com.coco.service.imp;

import com.coco.mapper.ShopMapper;
import com.coco.pojo.Shop;
import com.coco.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImp implements ShopService {
    @Autowired
    ShopMapper shopMapper;
    @Override
    public List<Shop> findShop() {
        List<Shop> shops=shopMapper.findAllShop();
        return shopMapper.findAllShop();
    }
}
