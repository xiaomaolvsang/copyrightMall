package com.copyright.mall.controller;

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
public abstract class BaseController {

    @Resource
    private HttpServletRequest request;

    @Resource
    private JwtService jwtService;

    public Long getUserId() {
        String token = request.getHeader("X-Mall-TOKEN");
        try {
            String userOpenId = jwtService.getClaimFromToken(token).getSubject();
        }catch (Exception e){
            log.warn("解析",e);
        }
        return 1234L;
    }

    public Long getMallId() {
        String mallId = request.getHeader("X-Mall-Id");
        return StringUtils.isNotBlank(mallId) ? Long.valueOf(mallId) : -1L;
    }
}
