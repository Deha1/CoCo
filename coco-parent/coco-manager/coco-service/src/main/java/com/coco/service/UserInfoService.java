package com.coco.service;

import com.coco.pojo.UserInfo;


public interface UserInfoService {
    public UserInfo findUserInfo(int id);
    public void InsertUserInfo(int id);
    public void UpdateUserInfo(UserInfo userInfo);

}
