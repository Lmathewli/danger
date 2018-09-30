package com.life.full.danger.weixin.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author LiHongHui
 * @date 2018/9/29 17:42
 * @description
 */
@Component
public class WechatMpConfig {
    @Value("${weixin.mp.appId}")
    private String appId;

    @Value("${weixin.mp.appSecret}")
    private String appSecret;
    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        WxMpConfigStorage wxMpConfigStorage = wxMpConfigStorage();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage);
        return wxMpService;
    }
    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId(appId);
        wxMpConfigStorage.setSecret(appSecret);
        return wxMpConfigStorage;
    }
}
