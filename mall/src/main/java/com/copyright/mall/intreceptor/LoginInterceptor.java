package com.copyright.mall.intreceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : zhangyuchen
 * @date : 2019/12/1 10:27
 */
@Slf4j
@Component
@WebFilter(filterName = "test", urlPatterns = "/*" )
public class LoginInterceptor implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String path = ((HttpServletRequest) servletRequest).getRequestURI();
       /* if (path.startsWith("/v1/user/login")||
            path.startsWith("/swagger")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String token = ((HttpServletRequest) servletRequest).getHeader("X-Mall-TOKEN");
            if(StringUtils.isBlank(token)){
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.sendError(401);
            }
        }*/
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
