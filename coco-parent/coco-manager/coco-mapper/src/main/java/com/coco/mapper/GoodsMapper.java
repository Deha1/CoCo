package com.coco.mapper;

import com.coco.pojo.Goods;
import com.coco.pojo.GoodsPic;;import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);

    public Goods selectGoods(int id);

    List<Goods> selectByname(String goodsName);
    List<Goods> selectByshopid(Integer shopid);
    List<Goods> selectBykind(Integer kindid);
    List<Goods> selectFive();
    public List<Goods> findGoodbyId(int shop_id);

    public Goods findshopId(int id);

}