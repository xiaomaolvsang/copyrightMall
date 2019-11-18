package com.copyright.mall.controller;

import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author : zhangyuchen
 * @date : 2019/11/18 10:45
 */
public abstract class BaseController {

    @Resource
    private HttpServletRequest request;

    public Long getUserId() {
        return 1234L;
    }

    public Long getMallId() {
        String mallId = request.getHeader("X-Mall-Id");
        return StringUtils.isNotBlank(mallId) ? Long.valueOf(mallId) : -1L;
    }
}
