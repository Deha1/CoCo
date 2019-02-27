package com.coco.mapper;

import com.coco.pojo.Foot;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FootMapper {

    public void insertFoot(Foot co);

    public void updateFoot(Foot co);

    public List<Foot> findAllFootByUserId(int user_id);

    public int deleteAllFoot(int userId);

    public Foot findFootById(@Param("goodsId") int goods_id, @Param("userId") int user_id);



}
