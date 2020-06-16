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
public class V2WeChatAppInfo {

    @Value("${weChat.v2.appId}")
    private String appId;

    @Value("${weChat.v2.appSecret}")
    private String appSecret;

    @Value("$.weChat.v2.grantType}")
    private String grantType;

}
