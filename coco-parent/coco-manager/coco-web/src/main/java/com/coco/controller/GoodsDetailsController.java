package com.coco.controller;

import com.coco.common.ServerResponse;
import com.coco.pojo.Goods;
import com.coco.pojo.User;
import com.coco.service.FootService;
import com.coco.service.SelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class GoodsDetailsController {
    @Autowired
    private SelectService selectService;
    @Autowired
    private FootService footService;


    @ResponseBody
    @RequestMapping("/goodsDetails.do")
    public ServerResponse getGoods(@RequestParam("goodsId") Integer goodsId, HttpSession session){


        //User user = (User) session.getAttribute("user");
        User user=new User();
        user.setId(1);
        Goods ag = new Goods();
        ag.setId(goodsId);
        if(user!=null) {
            //增加一条足迹
            footService.addFoot(ag, user.getId());
        }

        //查询商品详情
        ServerResponse serviceRespson = selectService.findGoodsDetails(ag);

        System.out.println(serviceRespson.toString());

        return serviceRespson;
    }


}
