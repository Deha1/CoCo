package com.coco.mapper;

import com.coco.pojo.OrdersInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrdersInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrdersInfo record);

    int insertSelective(OrdersInfo record);

    OrdersInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrdersInfo record);

    int updateByPrimaryKey(OrdersInfo record);

    void batchInsert(@Param("orderInfoList") List<OrdersInfo> orderInfoList);

    List<OrdersInfo> getByOrderNoUserId(@Param("orderNo")Long orderNo, @Param("userId")Integer userId);

    List<OrdersInfo> getByOrderNo(@Param("orderNo")Long orderNo);
}