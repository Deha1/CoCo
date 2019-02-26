package com.coco.service;

import com.coco.commom.ServerResponse;
import com.coco.commom.vo.OrdersVo;
import com.github.pagehelper.PageInfo;

public interface OrdersService {
    ServerResponse createOrder(Integer userId)throws Exception;
    ServerResponse<String> cancel(Integer userId,Long orderNo);
    ServerResponse<OrdersVo> getOrderDetail(Integer userId, Long orderNo);
    ServerResponse<PageInfo> getOrderList(Integer userId, int pageNum, int pageSize);

}
