package com.coco.controller;

import com.coco.commom.Const;
import com.coco.commom.ResponseCode;
import com.coco.commom.ServerResponse;
import com.coco.pojo.Address;
import com.coco.pojo.User;
import com.coco.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

//import com.coco.pojo.Users;
//import com.coco.service.GuestService;
//import org.springframework.ui.Model;
//import org.springframework.web.HttpSessionRequiredException;


@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;



    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        ServerResponse<User> response = userService.login(username, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }

        return response;
    }

    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }


    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(@Valid User user, Errors errors) {
        if(errors.hasErrors())
        {
            System.out.println(errors);//服务端错误打印至后台
            return ServerResponse.createByErrorMessage("注册条件不符，请重新注册");
        }
        return userService.register(user);
    }

    @RequestMapping(value = "checkValid.do")
    @ResponseBody
    public ServerResponse<String> checkValid(String str, String type) {
        return userService.checkValid(str, type);
    }


    @RequestMapping(value = "getUserInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }

        return ServerResponse.createBySuccess(user);
    }

    @RequestMapping(value = "forgetQuestion.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetQuestion(String username) {
        return userService.selectQuestion(username);
    }


    @RequestMapping(value = "forgetCheckAnswer.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer) {
        return userService.checkAnswer(username, question, answer);

    }


    @RequestMapping(value = "forgetResetPassword.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetResetPassword(String username, String password, String forgetToken) {
        return userService.forgetResetPassword(username, password, forgetToken);
    }


    @RequestMapping(value = "resetPassword.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session, String passwordOld, String passwordNew) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return userService.resetPassword(passwordOld, passwordNew, user);
    }


    @RequestMapping(value = "updateInformation.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> updateInformation(HttpSession session, User user) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());
        ServerResponse<User> response = userService.updateInformation(user);
        if (response.isSuccess()) {
            response.getData().setUsername(currentUser.getUsername());
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;

    }


    @RequestMapping(value = "getInfo.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getInfo(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,需要强制登录status=10");
        }

        return userService.getInfo(user.getId());
    }

    @RequestMapping(value = "getAddress.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<Address>> getAddress(HttpSession session)
    {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user==null)
        {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,需要强制登录status=10");
        }
        return userService.getAddress(user.getId());
    }

    @RequestMapping(value = "addAddress.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> addAddress(HttpSession session, @Valid Address address,  Errors errors)
    {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user==null)
        {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录,需要强制登录status=10");
        }
        if(errors.hasErrors())
        {
            return ServerResponse.createByErrorMessage("添加新地址条件不符合，请重新添加");
        }
        return userService.addAddress(address,user);
    }


    @RequestMapping(value = "updateAddress.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Address> updateAddress(HttpSession session, Address address)
    {
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        address.setId(address.getId());
        address.setUserId(user.getId());
        ServerResponse<Address> response = userService.updateAddress(address,user);
        return response;
    }

    @RequestMapping(value = "deleteAddress.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> deleteAddress(Integer id)
    {
        ServerResponse<String> response = userService.deleteAddress(id);
        return response;
    }



}