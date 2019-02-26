package com.coco.pojo;

import java.util.Date;

public class ShopCar {
    private Integer id;

    private Integer userId;

    private Integer shopId;

    private Integer goodsId;

    private Integer num;

    private Integer checked;

    private Date createTime;

    private Date updateTime;

    public ShopCar(Integer id, Integer userId, Integer shopId, Integer goodsId, Integer num, Integer checked, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.shopId = shopId;
        this.goodsId = goodsId;
        this.num = num;
        this.checked = checked;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ShopCar() {
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

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
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
}