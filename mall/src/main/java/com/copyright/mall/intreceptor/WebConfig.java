package com.copyright.mall.intreceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfig
 *
 * @author lijian
 * @version 1.0
 * @date 2019/12/20 2:34 下午
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
      LoginInterceptor loginInterceptor = new LoginInterceptor();
      InterceptorRegistration loginRegistry = registry.addInterceptor(loginInterceptor);
      loginRegistry.addPathPatterns("/**");

      loginRegistry.excludePathPatterns("/product/*");
      loginRegistry.excludePathPatterns("/class/*");
      loginRegistry.excludePathPatterns("/banner/*");
      loginRegistry.excludePathPatterns("/opus/*");
      loginRegistry.excludePathPatterns("/detail/*");
      loginRegistry.excludePathPatterns("/checkHealth");
      loginRegistry.excludePathPatterns("/v1/user/login");
      loginRegistry.excludePathPatterns("/swagger");
    }
}
