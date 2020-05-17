package com.copyright.mall.manage.controller;

import com.copyright.mall.aspect.ControllerErro;
import com.copyright.mall.bean.Classification;
import com.copyright.mall.manage.domain.vo.ClassResp;
import com.copyright.mall.service.impl.ClassificationService;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "后端分类管理")
@Slf4j
@RestController
@RequestMapping("/manage/v1/class")
public class ManageClassController {

    private ClassificationService classificationService;


    @ApiOperation(value = "后端分类查询")
    @GetMapping("/getClass")
    @ControllerErro
    public Wrapper<List<ClassResp>> test(){
        List<Classification> classifications = classificationService.getAll();
        List<ClassResp> classResps = new ArrayList<>();
        classifications.forEach(classification -> {
            ClassResp classResp = new ClassResp();
            BeanUtils.copyProperties(classification,classResp);
            classResps.add(classResp);
        });
        return WrapMapper.ok(classResps);
    }
}
