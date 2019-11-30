package com.copyright.mall.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author : zhangyuchen
 * @date : 2019/11/30 21:24
 */
@Component
@Data
public class WeChatAppInfo {

    @Value("${weChat.appId}")
    private String appId;

    @Value("${weChat.appSecret}")
    private String appSecret;

    @Value("${weChat.grantType}")
    private String grantType;

}
