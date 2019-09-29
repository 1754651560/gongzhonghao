package com.zhangman.gongzhonghao.service;

import com.zhangman.gongzhonghao.constant.WXConstants;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @Author 张满
 * @Description  微信公众号service
 * @Date 2019/9/29 21:06
 * @vsersion 1.0.0
 **/
@Service
public class WXServiceImpl implements WXService {


    /**
     * @Author 张满
     * @Description 检验签名是否正确
     * @Date 2019/9/29  21:11
     * @Param [signature, timestamp, nonce]
     * @return boolean
     **/
    @Override
    public boolean checkSignature(String signature, String timestamp, String nonce) {

//        1）将token、timestamp、nonce三个参数进行字典序排序\
        String[] strArray = new String[]{WXConstants.TOKEN,timestamp,nonce};
        Arrays.sort(strArray);
//        2）将三个参数字符串拼接成一个字符串进行sha1加密
        String empStr = strArray[0] + strArray[1] + strArray[2];
        String mySignature = sha1(empStr);
//        3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        if(mySignature.equalsIgnoreCase(signature)){
            return true;
        }else {
            return false;
        }

    }

    /**
     * @Author 张满
     * @Description 对字符串进行sha1加密
     * @Date 2019/9/29  21:18
     * @Param [empStr]
     * @return java.lang.String
     **/
    private String sha1(String empStr) {

        try {
            MessageDigest md = MessageDigest.getInstance("sha1");
            byte[] digest = md.digest(empStr.getBytes());
            char[] chars = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f',};
            StringBuilder sb = new StringBuilder();
            for (byte b : digest){
                sb.append(chars[(b>>4)&15]);
                sb.append(chars[b&15]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }


}
