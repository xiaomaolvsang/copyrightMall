package com.copyright.mall.manage.filter;

import com.alibaba.fastjson.JSON;
import com.copyright.mall.service.JwtService;
import com.copyright.mall.util.UserUtils;
import com.copyright.mall.util.wrapper.WrapMapper;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "manageUserFilter", urlPatterns = {"/manage/*"})
@Order(2)
public class ManageUserFilter implements Filter {

    @Resource
    private JwtService jwtService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("ManageUserFilter  init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if (((HttpServletRequest) request).getRequestURL().toString().contains("/login")) {
            chain.doFilter(request, response);
            return;
        }
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type,X-MANAGE-TOKEN");

        // 如果是option请求，直接返回200
        if (httpServletRequest.getMethod().equals(HttpMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            return;
        }


        String token = httpServletRequest.getHeader("X-MANAGE-TOKEN");
        if (StringUtils.isBlank(token)) {
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            httpServletResponse.getOutputStream().println(JSON.toJSONString(WrapMapper.wrap(HttpStatus.UNAUTHORIZED.value()
                    , "Invalid authentication")));
            httpServletResponse.getOutputStream().close();
            return;
        }

        String userId = null;
        try {
            userId = jwtService.getClaimFromToken(token).getSubject();
        } catch (Exception e) {
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            httpServletResponse.getOutputStream().println(JSON.toJSONString(WrapMapper.wrap(HttpStatus.UNAUTHORIZED.value(),
                    "Invalid authentication")));
            httpServletResponse.getOutputStream().close();
            return;
        }
        if (userId != null) {
            UserUtils.setUserId(Long.valueOf(userId));
        }
        Claims claims = jwtService.getClaimFromToken(token);
        if (claims != null) {
            if (claims.get("shop") != null) {
                UserUtils.setShopIds((List<Long>) claims.get("shop"));
            }
            if (claims.get("roles") != null) {
                UserUtils.setRoleIds((List<Long>) claims.get("roles"));
            }
            if (claims.get("mallId") != null) {
                UserUtils.setMallId(Long.valueOf(claims.get("mallId").toString()));
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
