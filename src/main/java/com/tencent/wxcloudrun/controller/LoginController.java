package com.tencent.wxcloudrun.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    /**
     * 获取用户openid, 并将session存入redis，发送到前端
     * @param code
     * @return
     */
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public Map<String, Object> appDoLogin(@RequestBody  Map<String,String > codeMap){
        System.out.println(codeMap);
        Map<String, Object> map = new HashMap<>();
        if(codeMap.get("code") == null){
            map.put("success", false);
            return map;
        }

        String url = new ComonUtils().appendUrl(codeMap.get("code")).toString();
        RestTemplate restTemplate = new RestTemplate();

        //用restTemplate请求url，得到openid和session_key

        String reponse = restTemplate.getForObject(url, String.class);
        if(reponse == null){
            map.put("success", false);
            return map;
        }

        System.out.println(reponse);
        String openid = "";
        String session_key = "";
        String[] strings = reponse.split(",");
        openid = strings[1].substring(10, strings[1].length()-2);
        session_key = strings[0].substring(16, strings[0].length()-1);

        AppUserModel appUserModel = new AppUserModel();
        appUserModel.setOpenid(openid);
        appUserModel.setSession_key(session_key);

        //将openid和session_key存入redis,之后传到前端的sessionId就是
        //"userSession:" + appUserModel.getOpenid()

//        redis.set("userSession:" + appUserModel.getOpenid(), appUserModel.getSession_key(),
//                60*10);
        //System.out.println(openid);
        map.put("sessionId","userSession:" + appUserModel.getOpenid());
        map.put("success", true);
        System.out.println(map);
        return map;
    }

}
