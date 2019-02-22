package com.coco.controller;

import com.coco.common.ServerResponse;
import com.coco.pojo.Picture;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PictureChooseController {

    @ResponseBody
    @RequestMapping("/choosepicture.do")
    public ServerResponse choosePicture(){
        Picture picture=new Picture();
        List<String> list=new ArrayList<>();
        list.add(picture.getA1());
        list.add(picture.getA2());
        list.add(picture.getA3());
        list.add(picture.getA4());
        list.add(picture.getA5());

        for (String p:list){
            System.out.println(p.toString());
        }
        System.out.println(list);


        System.out.println(ServerResponse.createBySuccess(list));
        return ServerResponse.createBySuccess(list);
    }
}
