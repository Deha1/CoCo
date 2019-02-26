package com.coco.pojo;

import java.util.Date;

public class UserInfo {
    private Integer id;

    private Integer userId;

    private String userName;

    private String userPic;

    private Integer sex;

    private String phone;

    private String brithday;

    private String mood;

    private Date createTime;

    private Date updateTime;

    public UserInfo(Integer id, Integer userId, String userName, String userPic, Integer sex, String phone, String brithday, String mood, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.userPic = userPic;
        this.sex = sex;
        this.phone = phone;
        this.brithday = brithday;
        this.mood = mood;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public UserInfo() {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic == null ? null : userPic.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getBrithday() {
        return brithday;
    }

    public void setBrithday(String brithday) {
        this.brithday = brithday == null ? null : brithday.trim();
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood == null ? null : mood.trim();
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