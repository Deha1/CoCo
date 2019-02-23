package com.coco.service.imp;

import com.coco.mapper.GoodsPicMapper;
import com.coco.pojo.GoodsPic;
import com.coco.service.GoodPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodPicServiceImp implements GoodPicService {
    @Autowired
    private GoodsPicMapper goodsPicMapper;
    @Override
    public GoodsPic findshopId(int goodId) {
        GoodsPic goodsPic=goodsPicMapper.findGoodsPic(goodId);
        return goodsPic;
    }
}
