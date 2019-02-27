package com.coco.pojo;

import java.util.Date;

public class Foot implements Comparable<Foot> {
    private Integer id;

    private Integer userId;

    private Integer goodsId;

    private Date createTime;

    private Date updateTime;

    public Foot(Integer id, Integer userId, Integer goodsId, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.goodsId = goodsId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Foot() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public int compareTo(Foot o) {
        long key = this.updateTime.getTime()-o.getUpdateTime().getTime();
        if(key>0){
            return -1;
        }
        return 1;
    }
}