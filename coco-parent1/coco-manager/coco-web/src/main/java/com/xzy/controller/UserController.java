package com.xzy.controller;

import com.xzy.pojo.Users;
import com.xzy.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    private GuestService guestService;

    @RequestMapping("/toregist.htm")
    public String toregist(){

        return "regist";
    }
    @RequestMapping("/regist.htm")
    public String regist(Users u, Model mo){

        guestService.registUser(u);
        mo.addAttribute("user",u);
        return "success";
    }

    public GuestService getGuestService() {
        return guestService;
    }

    public void setGuestService(GuestService guestService) {
        this.guestService = guestService;
    }
}
