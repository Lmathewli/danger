package com.life.full.danger.weixin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author LiHongHui
 * @date 2018/9/28 15:37
 * @description
 *  基础版本
 */
@Slf4j
@RestController
@RequestMapping(("/weixin"))
public class WeiXinController {
    @Value("${weixin.mp.appId}")
    private String appId;

    @Value("${weixin.mp.appSecret}")
    private String appSecret;
    /**
     * 验证token
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @RequestMapping("/auth")
    public String auth(String signature, String timestamp, String nonce, String echostr) {
        log.error("signature={}, timestamp={}, nonce={}, echostr={}", signature, timestamp, nonce, echostr);
        return echostr;
    }

    /**
     * 获取用户基本信息，如openid等
     * @return
     */
    @RequestMapping("/user/agree")
    public String agree(@RequestParam("code") String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret=" + appSecret + "&code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String reponse = restTemplate.getForObject(url, String.class);
        log.info("reponse={}", reponse);
        return null;
    }

}
