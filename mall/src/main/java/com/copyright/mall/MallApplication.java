package com.copyright.mall;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages =  {"com.copyright.mall"},exclude = PageHelperAutoConfiguration.class)
@EnableTransactionManagement
@MapperScan(basePackages = {"com.copyright.mall.dao"})
@ImportResource(value = {"classpath:config/mybatis-config.xml"})
@EnableSwagger2
@ServletComponentScan(basePackages = {"com.copyright.mall.intreceptor","com.copyright.mall.manage.filter"})
public class MallApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class, args);
    }

}
