package com.copyright.mall.controller;

import com.copyright.mall.aspect.ControllerErro;
import com.copyright.mall.domain.dto.test.TestParam;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Wrapper<Boolean> test(@ApiParam TestParam testParam){
        log.info("test {}",testParam);
        return WrapMapper.ok(true);
    }
}
