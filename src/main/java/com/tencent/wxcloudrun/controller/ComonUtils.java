package com.tencent.wxcloudrun.controller;




/**
 * 封装微信小程序appid, secret js_code  为获得用户openId
 */
public class ComonUtils {

    public static StringBuffer appendUrl(String code) {

        StringBuffer info = new StringBuffer("https://api.weixin.qq.com/sns/jscode2session?");

        info.append("appid=").append("wx14f50882de91ec11").append("&");

        info.append("secret=").append("433d9ba79ac3a9458bde25cb951215cb").append("&");

        info.append("js_code=").append(code).append("&");

        info.append("grant_type=").append("authorization_code");

        return info;
    }
}

