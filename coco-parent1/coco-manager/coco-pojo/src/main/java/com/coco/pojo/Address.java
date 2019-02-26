package com.coco.pojo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Address {
    private Integer id;

    @NotNull(message="用户名不能为空")

    private Integer userId;

    @NotNull(message="收货人不能为空")
    private String receiverUsername;

    @NotNull(message="电话号码不能为空")
    @Length(min=11, max=11, message = "手机号长度必须为11位")
    private String receiverPhone;

    @NotNull(message="省份不能为空")
    private String province;

    @NotNull(message="城市不能为空")
    private String city;

    @NotNull(message="所在区不能为空")
    private String district;

    @NotNull(message="具体地址不能为空")
    private String details;

    @NotNull(message="邮编不能为空")
    private String postcode;

    @NotNull(message="是否默认地址不能为空")
    private Integer isDefault;

    private Date createTime;

    private Date updateTime;

    public Address(Integer id, Integer userId, String receiverUsername, String receiverPhone, String province, String city, String district, String details, String postcode, Integer isDefault, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.receiverUsername = receiverUsername;
        this.receiverPhone = receiverPhone;
        this.province = province;
        this.city = city;
        this.district = district;
        this.details = details;
        this.postcode = postcode;
        this.isDefault = isDefault;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Address() {
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

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername == null ? null : receiverUsername.trim();
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone == null ? null : receiverPhone.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
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