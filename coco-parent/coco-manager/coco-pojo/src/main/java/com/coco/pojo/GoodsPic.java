package com.coco.pojo;

public class GoodsPic {
    private Integer id;

    private Integer goodsId;

    private String url;

    public GoodsPic(Integer id, Integer goodsId, String url) {
        this.id = id;
        this.goodsId = goodsId;
        this.url = url;
    }

    public GoodsPic() {
        super();
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}