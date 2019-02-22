package com.coco.mapper;

import com.coco.pojo.Foot;

public interface FootMapper {
    int insert(Foot record);

    int insertSelective(Foot record);
}