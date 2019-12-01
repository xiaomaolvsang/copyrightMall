package com.copyright.mall.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : zhangyuchen
 * @date : 2019/11/30 21:21
 */
@Configuration
public class RestTemplateConfig {

    // 启动的时候要注意，由于我们在服务中注入了RestTemplate，所以启动的时候需要实例化该类的一个实例
    @Resource
    private RestTemplateBuilder builder;

    @Resource
    private ObjectMapper objectMapper;

    // 使用RestTemplateBuilder来实例化RestTemplate对象，spring默认已经注入了RestTemplateBuilder实例
    @Bean
    public RestTemplate restTemplate() {

        RestTemplate restTemplate = builder.build();

        List<HttpMessageConverter<?>> messageConverters = Lists.newArrayList();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);

        //不加可能会出现异常
        //Could not extract response: no suitable HttpMessageConverter found for response type [class ]

        MediaType[] mediaTypes = new MediaType[]{
                MediaType.APPLICATION_JSON,
                MediaType.APPLICATION_OCTET_STREAM,

                MediaType.TEXT_HTML,
                MediaType.TEXT_PLAIN,
                MediaType.TEXT_XML,
                MediaType.APPLICATION_STREAM_JSON,
                MediaType.APPLICATION_ATOM_XML,
                MediaType.APPLICATION_FORM_URLENCODED,
                MediaType.APPLICATION_JSON_UTF8,
                MediaType.APPLICATION_PDF,
        };

        converter.setSupportedMediaTypes(Arrays.asList(mediaTypes));

        try {
            //通过反射设置MessageConverters
            Field field = restTemplate.getClass().getDeclaredField("messageConverters");

            field.setAccessible(true);

            List<HttpMessageConverter<?>> orgConverterList = (List<HttpMessageConverter<?>>) field.get(restTemplate);

            Optional<HttpMessageConverter<?>> opConverter = orgConverterList.stream()
                    .filter(x -> x.getClass().getName().equalsIgnoreCase(MappingJackson2HttpMessageConverter.class
                            .getName()))
                    .findFirst();

            if (opConverter.isPresent() == false) {
                return restTemplate;
            }

            messageConverters.add(converter);//添加MappingJackson2HttpMessageConverter

            //添加原有的剩余的HttpMessageConverter
            List<HttpMessageConverter<?>> leftConverters = orgConverterList.stream()
                    .filter(x -> x.getClass().getName().equalsIgnoreCase(MappingJackson2HttpMessageConverter.class
                            .getName()) == false)
                    .collect(Collectors.toList());

            messageConverters.addAll(leftConverters);

            System.out.println(String.format("【HttpMessageConverter】原有数量：%s，重新构造后数量：%s"
                    , orgConverterList.size(), messageConverters.size()));

        } catch (Exception e) {
            e.printStackTrace();
        }

        restTemplate.setMessageConverters(messageConverters);

        return restTemplate;
    }
}
