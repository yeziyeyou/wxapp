package com.ruoyi.web.controller.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController2 {


    @Value("${wechat.app.id}")
    private String appid;

    @RequestMapping("/test2/hello")
    public String Hello(){
        return "hello";
    }

    @RequestMapping("/test2/appid")
    public String appid(){
        return appid;
    }
}
