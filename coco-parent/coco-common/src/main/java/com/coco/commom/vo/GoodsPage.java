package com.coco.commom.vo;

import java.math.BigDecimal;

public class GoodsPage {

    private int goods_id;
    private int shop_id;
    private String goods_name;
    private BigDecimal goods_price;
    private int top_show;
    private String goods_pic;
    private String shop_name;

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public BigDecimal getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(BigDecimal goods_price) {
        this.goods_price = goods_price;
    }

    public int getTop_show() {
        return top_show;
    }

    public void setTop_show(int top_show) {
        this.top_show = top_show;
    }

    public String getGoods_pic() {
        return goods_pic;
    }

    public void setGoods_pic(String goods_pic) {
        this.goods_pic = goods_pic;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    @Override
    public String toString() {
        return "GoodsPage{" +
                "goods_id=" + goods_id +
                ", shop_id=" + shop_id +
                ", goods_name='" + goods_name + '\'' +
                ", goods_price=" + goods_price +
                ", top_show=" + top_show +
                ", goods_pic='" + goods_pic + '\'' +
                ", shop_name='" + shop_name + '\'' +
                '}';
    }
}
