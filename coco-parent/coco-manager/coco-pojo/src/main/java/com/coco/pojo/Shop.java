package com.coco.pojo;

public class Shop {
    private Integer id;

    private String shopName;

    private String shopDis;

    private String shopPic;

    public Shop(Integer id, String shopName, String shopDis, String shopPic) {
        this.id = id;
        this.shopName = shopName;
        this.shopDis = shopDis;
        this.shopPic = shopPic;
    }

    public Shop() {
        super();
    }

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
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getShopDis() {
        return shopDis;
    }

    public void setShopDis(String shopDis) {
        this.shopDis = shopDis == null ? null : shopDis.trim();
    }

    public String getShopPic() {
        return shopPic;
    }

    public void setShopPic(String shopPic) {
        this.shopPic = shopPic == null ? null : shopPic.trim();
    }
}