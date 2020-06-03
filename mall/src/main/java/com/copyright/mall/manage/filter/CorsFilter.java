package com.copyright.mall.manage.filter;


import org.springframework.http.HttpMethod;
import org.springframework.util.PropertyPlaceholderHelper;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebFilter(filterName = "corsFilter", urlPatterns = {"/manage/*"})
public class CorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type,X-MANAGE-TOKEN");

        // 如果是option请求，直接返回200
        if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        chain.doFilter(req, res);
    }

    public static void main(String[] args) {
        PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}");
        String text = "测试${text}替换";
        Properties props = new Properties();
        props.setProperty("text", "文本");
        System.out.println(helper.replacePlaceholders(text,props));
    }
}