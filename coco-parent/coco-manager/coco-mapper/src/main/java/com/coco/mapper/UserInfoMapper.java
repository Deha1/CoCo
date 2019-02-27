package com.coco.mapper;

import com.coco.pojo.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    public UserInfo findUserInfoById(int user_id);

    public void InsertUserInfo(int id);

    public void updateUserInfo(UserInfo userInfo);
}