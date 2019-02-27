package com.coco.controller;


import com.coco.commom.ServerResponse;
import com.coco.pojo.Picture;
import com.coco.pojo.User;
import com.coco.pojo.UserInfo;
import com.coco.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;


    @ResponseBody
    @RequestMapping("/myuserinfo.do")
    public ServerResponse good(HttpSession session){
        User user=new User();
        user.setId(2);
        //User user=(User) session.getAttribute("user");
        if(user==null){
            System.out.println(ServerResponse.createByErrorMessage("用户未登录"));
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        int useId=user.getId();
        UserInfo userInfo1=userInfoService.findUserInfo(useId);


        System.out.println(userInfo1.toString());

        return ServerResponse.createBySuccess(userInfo1);
    }

    @ResponseBody
    @RequestMapping("/userinfoUpdate.do")


    public ServerResponse good1(UserInfo userInfo, HttpSession session){

        User user1=new User();
        user1.setId(1);
        session.setAttribute("user",user1);
        User user=(User) session.getAttribute("user");
        if(user==null){
            System.out.println(ServerResponse.createByErrorMessage("用户未登录"));
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        int userId=user.getId();


        Picture picture=new Picture();
        UserInfo userInfo1 =new UserInfo();
        userInfo1.setId(userId);
        userInfo1.setUserId(userId);
        userInfo1.setUserName(userInfo.getUserName());
        userInfo1.setUserPic(userInfo.getUserPic());
        userInfo1.setSex(userInfo.getSex());
        userInfo1.setPhone(userInfo.getPhone());
        userInfo1.setMood(userInfo.getMood());

        //System.out.println(userInfo1.toString());
        userInfoService.UpdateUserInfo(userInfo1);
        //System.out.println(userInfo1.toString());
        //System.out.println(ServerResponse.createBySuccess(userInfo1).toString());
        System.out.println(ServerResponse.createBySuccess(userInfo1));
        return ServerResponse.createBySuccess(userInfo1);
    }

}
