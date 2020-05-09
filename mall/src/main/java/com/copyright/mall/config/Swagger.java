package com.copyright.mall.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author : zhangyuchen
 * @date : 2019/10/22 14:03
 */
@Configuration
@EnableSwagger2
public class Swagger {


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.copyright.mall"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(security())
                .securityContexts(Collections.singletonList(securityContext()));
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("mall后端接口")
                //创建人
                .contact(new Contact("zyc","",""))
                //版本号
                .version("1.0")
                //描述
                .description("mall后端接口")
                .build();
    }

    private List<ApiKey> security() {
        return Lists.newArrayList(
                new ApiKey("token", "X-Mall-TOKEN", "header"),
                new ApiKey("manageToken", "X-MANAGE-TOKEN", "header")
        );
    }
    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth())
                .forPaths(PathSelectors.any()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope(
                "global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(
                new SecurityReference("token", authorizationScopes),
                new SecurityReference("manageToken", authorizationScopes));
    }



}
