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
public class DeleteFootsConroller {
    @Autowired
    private FootService footService;

    @ResponseBody
    @RequestMapping("/delete.do")
    public ServerResponse deAllFoots(HttpSession session){

        //User user =(User)session.getAttribute("user");
        User user = new User();
        user.setId(1);
        if(user==null){
            return ServerResponse.createByErrorMessage("用户未登录，请先登录");
        }

        return footService.deleteAllFoot(user.getId());
    }

}
