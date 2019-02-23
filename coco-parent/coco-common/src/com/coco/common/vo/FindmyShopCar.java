package com.coco.common.vo;

public class FindmyShopCar {
    private Integer id;

    private Integer goodsId;

    private Integer num;

    private String pic;

    private String shopName;

    private String goodName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "FindmyShopCar{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", num=" + num +
                ", pic='" + pic + '\'' +
                ", shopName='" + shopName + '\'' +
                ", goodName='" + goodName + '\'' +
                '}';
    }
}
