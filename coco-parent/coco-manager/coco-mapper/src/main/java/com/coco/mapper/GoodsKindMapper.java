package com.coco.mapper;

import com.coco.pojo.GoodsKind;

public interface GoodsKindMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsKind record);

    int insertSelective(GoodsKind record);

    GoodsKind selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsKind record);

    int updateByPrimaryKey(GoodsKind record);
}