package com.coco.service.imp;

import com.coco.mapper.ShopMapper;
import com.coco.pojo.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Discountimp implements com.coco.service.Discount {
    @Autowired
    private ShopMapper shopMapper;

    @Override
    public List<Shop> selectByid() {


        return shopMapper.selectByid();
    }
}
