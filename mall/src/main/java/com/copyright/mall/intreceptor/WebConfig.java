package com.copyright.mall.intreceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * WebConfig
 *
 * @author lijian
 * @version 1.0
 * @date 2019/12/20 2:34 下午
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration loginRegistry = registry.addInterceptor(loginInterceptor);
        loginRegistry.addPathPatterns("/**");

        loginRegistry.excludePathPatterns("/product/*");
        loginRegistry.excludePathPatterns("/class/*");
        loginRegistry.excludePathPatterns("/banner/*");
        loginRegistry.excludePathPatterns("/opus/*");
        loginRegistry.excludePathPatterns("/detail/*");
        loginRegistry.excludePathPatterns("/checkHealth");
        loginRegistry.excludePathPatterns("/v1S/user/login");
        loginRegistry.excludePathPatterns("/webjars/**");
        loginRegistry.excludePathPatterns("/v2/*");
        //证书详情不鉴权
        loginRegistry.excludePathPatterns("/V1/copyright/detail/*");
    }
}
