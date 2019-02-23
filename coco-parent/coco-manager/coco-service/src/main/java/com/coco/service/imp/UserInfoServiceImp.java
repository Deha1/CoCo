package com.coco.service.imp;

import com.coco.mapper.UserInfoMapper;
import com.coco.pojo.UserInfo;
import com.coco.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImp implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public UserInfo findUserInfo(int id) {
        UserInfo userInfo=userInfoMapper.findUserInfoById(id);

        return userInfo;
    }

    @Override
    public void UpdateUserInfo(UserInfo userInfo) {
        userInfoMapper.updateUserInfo(userInfo);
    }

    @Override
    public void InsertUserInfo(int id) {
        userInfoMapper.InsertUserInfo(id);

    }
}
