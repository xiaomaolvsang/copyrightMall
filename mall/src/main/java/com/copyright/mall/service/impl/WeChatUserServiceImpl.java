package com.copyright.mall.service.impl;

import com.copyright.mall.config.WeChatAppInfo;
import com.copyright.mall.domain.dto.user.WeChatUserInfo;
import com.copyright.mall.domain.exception.BusinessException;
import com.copyright.mall.service.IWechatUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : zhangyuchen
 * @date : 2019/11/30 21:21
 */
@Service
@Slf4j
public class WeChatUserServiceImpl implements IWechatUserService {

    @Resource
    RestTemplate restTemplate;

    @Resource
    WeChatAppInfo weChatAppInfo;

    @Override
    public WeChatUserInfo weChatLogin(String jsCode) {
        Map<String,Object> param = new HashMap<>(4);
        param.put("appId",weChatAppInfo.getAppId());
        param.put("secret",weChatAppInfo.getAppSecret());
        param.put("js_code",jsCode);
        param.put("grant_type",weChatAppInfo.getGrantType());
        ResponseEntity<WeChatUserInfo> result =  restTemplate.getForEntity("https://api.weixin.qq.com/sns/jscode2session",WeChatUserInfo.class,param);
        if(!result.getStatusCode().is2xxSuccessful()){
            throw new BusinessException("调用微信登录失败");
        }
        if(result.getBody().getErrcode()==40029){
            log.warn("code 无效");
            throw new BusinessException("登录失败");
        }
        if(result.getBody().getErrcode()==45011){
            log.warn("被限流");
            throw new BusinessException("登录失败");
        }
        if(result.getBody().getErrcode()==-1){
            log.warn("微信服务器异常");
            throw new BusinessException("登录失败");
        }
        
        return null;
    }
}
