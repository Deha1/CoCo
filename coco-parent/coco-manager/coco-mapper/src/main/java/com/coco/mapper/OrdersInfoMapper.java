package com.coco.mapper;

import com.coco.pojo.OrdersInfo;

public interface OrdersInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrdersInfo record);

    int insertSelective(OrdersInfo record);

    OrdersInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrdersInfo record);

    int updateByPrimaryKey(OrdersInfo record);
}