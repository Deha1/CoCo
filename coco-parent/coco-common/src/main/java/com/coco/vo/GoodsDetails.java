package com.coco.vo;

import java.math.BigDecimal;
import java.util.List;

public class GoodsDetails {

    private int goods_id;
    private int kind_id;
    private int shop_id;
    //商品名
    private String name;
    //副标题
    private String subtitle;
    //详情描述
    private String detail;
    //价格
    private BigDecimal price;
    //数量
    private int num;

    //1-在售，2-下线
    private String status;
    //热门计数
    private int top_show;
    //商铺名
    private String shop_name;
    //种类
    private String kind_name;
    //图片地址
    private List<String> pic_url;

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public int getKind_id() {
        return kind_id;
    }

    public void setKind_id(int kind_id) {
        this.kind_id = kind_id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTop_show() {
        return top_show;
    }

    public void setTop_show(int top_show) {
        this.top_show = top_show;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getKind_name() {
        return kind_name;
    }

    public void setKind_name(String kind_name) {
        this.kind_name = kind_name;
    }

    public List<String> getPic_url() {
        return pic_url;
    }

    public void setPic_url(List<String> pic_url) {
        this.pic_url = pic_url;
    }

    @Override
    public String toString() {
        return "GoodsDetails{" +
                "goods_id=" + goods_id +
                ", kind_id=" + kind_id +
                ", shop_id=" + shop_id +
                ", name='" + name + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", detail='" + detail + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", status='" + status + '\'' +
                ", top_show=" + top_show +
                ", shop_name='" + shop_name + '\'' +
                ", kind_name='" + kind_name + '\'' +
                ", pic_url=" + pic_url +
                '}';
    }



}
