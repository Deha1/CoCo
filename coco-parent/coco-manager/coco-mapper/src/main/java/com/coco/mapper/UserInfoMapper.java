package com.coco.mapper;

import com.coco.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {
    public UserInfo findUserInfoById(int user_id);

    public void InsertUserInfo(int id);

    public void updateUserInfo(UserInfo userInfo);
}