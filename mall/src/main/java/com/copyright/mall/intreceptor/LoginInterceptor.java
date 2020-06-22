package com.copyright.mall.intreceptor;

import com.copyright.mall.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : zhangyuchen
 * @date : 2019/12/1 10:27
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURL().toString().contains("swagger")) {
            return true;
        }
        if (request.getRequestURL().toString().contains("zmanage")) {
            return true;
        }
        if (request.getRequestURL().toString().toUpperCase().contains("v2".toUpperCase())) {
            return true;
        }
        String token = request.getHeader("X-Mall-TOKEN");
        if (StringUtils.isBlank(token) || jwtService.isTokenExpired(token)) {
            response.setStatus(401);
            return false;
        }
        return true;
    }
}
