package com.zhangman.gongzhonghao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author 张满
 * @Description Test Controller
 * @Date 2019/9/29 17:29
 * @vsersion 1.0.0
 **/
@Controller
public class HelloController {


    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "hello";
    }


}
