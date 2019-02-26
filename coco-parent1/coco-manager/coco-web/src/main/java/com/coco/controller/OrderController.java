package com.coco.controller;


import com.coco.commom.ResponseCode;
import com.coco.commom.ServerResponse;
import com.coco.pojo.User;
import com.coco.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order/")
public class OrderController {

    @Autowired
    private OrdersService ordersService;

    @ResponseBody
    @RequestMapping("create.do")
    public ServerResponse create(HttpSession session)throws Exception {
        //User user = (User) session.getAttribute(Const.CURRENT_USER);
        User user = new User();
        user.setId(2);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
            ServerResponse serverResponse= ordersService.createOrder(user.getId());
        System.out.println(serverResponse.toString());
        return serverResponse;
    }
    @RequestMapping("cancel.htm")
    @ResponseBody
    public ServerResponse cancel(HttpSession session, Long orderNo){
        //User user = (User)session.getAttribute(Const.CURRENT_USER);
        User user = new User();
        user.setId(2);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        ServerResponse serverResponse=ordersService.cancel(user.getId(),orderNo);
        System.out.println(serverResponse.toString());
        return serverResponse;
    }

    @RequestMapping("detail.htm")
    @ResponseBody
    public ServerResponse detail(HttpSession session,Long orderNo){
        //User user = (User)session.getAttribute(Const.CURRENT_USER);
        User user = new User();
        user.setId(2);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return ordersService.getOrderDetail(user.getId(),orderNo);
    }

    @RequestMapping("list.htm")
    @ResponseBody
    public ServerResponse list(HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "5") int pageSize){
        //User user = (User)session.getAttribute(Const.CURRENT_USER);
        User user = new User();
        user.setId(2);
        if(user ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return ordersService.getOrderList(user.getId(),pageNum,pageSize);
    }




}
