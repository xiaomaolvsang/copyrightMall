package com.copyright.mall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.copyright.mall.bean.User;
import com.copyright.mall.config.WeChatAppInfo;
import com.copyright.mall.domain.dto.user.WeChatUserInfo;
import com.copyright.mall.domain.exception.BusinessException;
import com.copyright.mall.service.IUserService;
import com.copyright.mall.service.IWechatUserService;
import com.copyright.mall.util.AES;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    private RestTemplate restTemplate;

    @Resource
    private WeChatAppInfo weChatAppInfo;
    @Resource
    private IUserService userService;
    @Override
    public WeChatUserInfo weChatLogin(String jsCode) {
        Map<String,Object> param = new HashMap<>(4);
        param.put("appid",weChatAppInfo.getAppId());
        param.put("secret",weChatAppInfo.getAppSecret());
        param.put("js_code",jsCode);
        param.put("grant_type",weChatAppInfo.getGrantType());
        ResponseEntity<WeChatUserInfo> result = restTemplate.getForEntity("https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={js_code}&grant_type={grant_type}", WeChatUserInfo.class,param);
        if(!result.getStatusCode().is2xxSuccessful()){
            throw new BusinessException("调用微信登录失败");
        }
        if(StringUtils.isNotBlank(result.getBody().getOpenid())){
            return result.getBody();
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
        if(result.getBody().getErrcode()!=0){
            log.warn("微信登录异常[{}]",result.getBody().getErrmsg());
            throw new BusinessException("微信登录未知异常");
        }

        return result.getBody();
    }

    @Override
    public WeChatUserInfo getSensitiveData(Long userId,String encryptedData, String iv) {
        User user = userService.selectByUserId(userId);
        if(user==null){
            return null;
        }
        JSONObject wechatResult =  AES.decrypt(weChatAppInfo.getAppId(),encryptedData,user.getSessionKey(),iv);
        WeChatUserInfo result = new WeChatUserInfo();
        result.setPhone(wechatResult.getString("phoneNumber"));
        return result;
    }


}
