package com.zhangman.gongzhonghao.service;

public interface WXService {


    boolean checkSignature(String signature, String timestamp, String nonce);
}
