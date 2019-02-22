package com.coco.controller;

import com.coco.common.Const;
import com.coco.common.ResponseCode;
import com.coco.common.ServerResponse;
import com.coco.pojo.User;
//import com.coco.pojo.Users;
//import com.coco.service.GuestService;
import com.coco.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;



@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="login.htm",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session)
    {
        ServerResponse<User> response=userService.login(username,password);
        if(response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }

        return response;
    }

    @RequestMapping(value="logout.htm",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session)
    {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }


    @RequestMapping(value="register.htm",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user)
    {
        return userService.register(user);
    }

    @RequestMapping(value="checkVaild.htm")
    @ResponseBody
    public ServerResponse<String>  checkVaild(String str ,String type)
    {
        return userService.checkValid(str,type);
    }


    @RequestMapping(value="getUserInfo.htm",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session)
    {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user==null)
        {
            return ServerResponse.createByErrorMessage("用户未登录");
        }

        return ServerResponse.createBySuccess(user);
    }

    @RequestMapping(value="forgetQuestion.htm",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetQuestion(String username)
    {
        return userService.selectQuestion(username);
    }


    @RequestMapping(value="forgetCheckAnswer.htm",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username,String question,String answer)
    {
        return userService.checkAnswer(username,question,answer);

    }


    @RequestMapping(value="forgetResetPassword.htm",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetResetPassword(String username,String password,String forgetToken)
    {
        return userService.forgetResetPassword(username,password,forgetToken);
    }


    @RequestMapping(value="resetPassword.htm",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session,String passwordOld,String passwordNew)
    {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user==null)
        {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return userService.resetPassword(passwordOld,passwordNew,user);
    }


    @RequestMapping(value="updateInformation.htm",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> updateInformation(HttpSession session,User user)
    {
        User currentUser=(User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser==null)
        {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());
        ServerResponse<User> response=userService.updateInformation(user);
        if(response.isSuccess())
        {
            response.getData().setUsername(currentUser.getUsername());
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;

    }



    @RequestMapping(value = "getInfo.htm",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getInfo(HttpSession session)
    {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user==null)
        {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录,需要强制登录status=10");
        }

        return userService.getInfo(user.getId());

    }

//    @RequestMapping(value = "updateAddress.htm",method = RequestMethod.POST)
//    @ResponseBody
//    public ServerResponse<> updateAddress(User user)
//    {
//
//
//    }



//    @RequestMapping("/toregist.htm")
//    public String toregist(){
//
//        return "regist";
//    }
//    @RequestMapping("/regist.htm")
//    public String regist(Users u, Model mo){
//
//
//        return "success";
//    }


}
