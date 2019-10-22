package com.copyright.mall.controller;

import com.copyright.mall.domain.dto.test.TestParam;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.google.common.base.Stopwatch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author : zhangyuchen
 * @date : 2019/10/22 14:34
 */
@RestController
@Api(tags = {"测试接口"})
@RequestMapping("/test")
@Slf4j
public class TestController {


    @ApiOperation(value = "测试接口")
    @GetMapping("/test")
    public Wrapper<String> test(@ApiParam TestParam testParam){
        Stopwatch stopwatch = Stopwatch.createStarted();
        log.info("test {} 时间 [{}]",testParam,stopwatch.elapsed(TimeUnit.SECONDS));
        return WrapMapper.ok(String.format("时间 [%s]",new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date())));
    }
}
