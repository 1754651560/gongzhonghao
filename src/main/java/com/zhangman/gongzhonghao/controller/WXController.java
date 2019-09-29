package com.zhangman.gongzhonghao.controller;

import com.zhangman.gongzhonghao.service.WXService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author 张满
 * @Description 微信公众号controller
 * @Date 2019/9/29 20:46
 * @vsersion 1.0.0
 **/
@Controller
@RequestMapping("/wx")
public class WXController {


    @Autowired
    private WXService wxService;



    /**
     * @Author 张满
     * @Description 暴露给微信服务器，验证服务器
     * @Date 2019/9/29  21:04
     * @Param [request]
     * @return void
     **/
    @GetMapping("/checkSignature")
    public void checkSignature(HttpServletRequest request, HttpServletResponse response){
//        参数	描述
//        signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
//        timestamp	时间戳
//        nonce	随机数
//        echostr	随机字符串

        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        if(wxService.checkSignature(signature,timestamp,nonce)){

            PrintWriter writer = null;
            try {
                writer = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            writer.write(echostr);
            writer.flush();
            writer.close();
        }else {
            System.out.println("验证签名失败！");
        }

    }







}


















