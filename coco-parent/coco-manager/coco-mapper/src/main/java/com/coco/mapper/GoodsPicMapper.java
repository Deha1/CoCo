package com.coco.mapper;

import com.coco.pojo.GoodsPic;

public interface GoodsPicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsPic record);

    int insertSelective(GoodsPic record);

    GoodsPic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsPic record);

    int updateByPrimaryKey(GoodsPic record);
}