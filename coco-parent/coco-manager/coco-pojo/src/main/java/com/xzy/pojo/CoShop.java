package com.xzy.pojo;

public class CoShop {
    private Integer shopId;

    private String shopName;

    private String shopDis;

    private String shopPic;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
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