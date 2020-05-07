package com.copyright.mall.manage.controller;

import com.copyright.mall.bean.User;
import com.copyright.mall.domain.exception.BusinessException;
import com.copyright.mall.service.IUserService;
import com.copyright.mall.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author : zhangyuchen
 * @date : 2019/11/18 10:45
 */
@Slf4j
public abstract class BaseManageController {

    @Resource
    private HttpServletRequest request;

    @Resource
    private JwtService jwtService;

    @Resource
    private IUserService userService;

    public Long getUserId() {
        return 1L;
    }

    public String getUserPhone() {
        return "1";
    }

    public Long getMallId() {
        String mallId = request.getHeader("X-Mall-Id");
        return StringUtils.isNotBlank(mallId) ? Long.valueOf(mallId) : -1L;
    }
}
