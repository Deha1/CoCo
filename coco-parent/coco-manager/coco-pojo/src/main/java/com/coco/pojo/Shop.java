package com.coco.pojo;

public class Shop {
    private Integer id;

    private String shopName;

    private String shopDis;

    private String shopPic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopDis() {
        return shopDis;
    }

    public void setShopDis(String shopDis) {
        this.shopDis = shopDis;
    }

    public String getShopPic() {
        return shopPic;
    }

    public void setShopPic(String shopPic) {
        this.shopPic = shopPic;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", shopName='" + shopName + '\'' +
                ", shopDis='" + shopDis + '\'' +
                ", shopPic='" + shopPic + '\'' +
                '}';
    }
}