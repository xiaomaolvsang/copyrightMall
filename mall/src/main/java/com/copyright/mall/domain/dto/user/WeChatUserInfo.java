package com.copyright.mall.domain.dto.user;

import lombok.Data;

/**
 * @author : zhangyuchen
 * @date : 2019/11/30 21:18
 */
@Data
public class WeChatUserInfo {

    private String openid;
    private String session_key;
    private String unionId;
    private Integer errcode;
    private String errmsg;
    private String phone;
}
