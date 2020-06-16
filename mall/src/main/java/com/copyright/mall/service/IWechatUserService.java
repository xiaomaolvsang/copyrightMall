package com.copyright.mall.service;

import com.copyright.mall.domain.dto.user.WeChatUserInfo;

/**
 * @author : zhangyuchen
 * @date : 2019/11/30 21:17 */
public interface IWechatUserService {

    /**
     * 微信登录
     * @param jsCode 登录时获取的code
     * @return
     */
    WeChatUserInfo weChatLogin(String jsCode, String version);



    WeChatUserInfo getSensitiveData(Long userId,String encryptedData,String iv, String version);
}
