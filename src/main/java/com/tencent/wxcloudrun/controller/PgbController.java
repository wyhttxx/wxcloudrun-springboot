package com.tencent.wxcloudrun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pgb")
public class PgbController {
    @GetMapping("/getUser")
    public Map<String,Object> zzj(){
        System.out.println("微信小程序正在调用...");
        Map<String,Object> map = new HashMap<String, Object>();
        List<String> list = new ArrayList<String>();
        list.add("pgb");
        list.add("Cathy");
        map.put("list",list);
        System.out.println("微信小程序调用完成...");
        return map;
    }
}
