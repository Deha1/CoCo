package com.coco.service;

import com.coco.commom.ServerResponse;
import com.coco.commom.ServerResponse;
import com.coco.pojo.Goods;
import com.coco.commom.vo.GoodsPage;

public interface SelectService {

    public Goods getGoods(Goods goods);

    public ServerResponse findGoodsDetails(Goods goods);

    public GoodsPage goodsToGoodsPageVo(Goods my_goods);
}
