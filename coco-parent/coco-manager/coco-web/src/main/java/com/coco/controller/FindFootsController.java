package com.coco.controller;

import com.coco.commom.ServerResponse;
import com.coco.pojo.User;
import com.coco.service.FootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class FindFootsController {

    @Autowired
    private FootService footService;

    @ResponseBody
    @RequestMapping("/findFoots.do")
    public ServerResponse findFoots( HttpSession session){

        User user = (User) session.getAttribute("user");
        if(user==null){
            return ServerResponse.createByErrorMessage("用户未登录，请先登录");
        }
//        User user = new User();


        ServerResponse serviceRespson = footService.findFoot(user.getId());

        System.out.println(serviceRespson.toString());

        return serviceRespson;
    }

}
