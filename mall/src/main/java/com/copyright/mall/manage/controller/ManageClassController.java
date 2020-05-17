package com.copyright.mall.manage.controller;


import com.copyright.mall.bean.Classification;
import com.copyright.mall.bean.resp.classification.ManageClassResp;
import com.copyright.mall.service.IClassificationService;
import com.copyright.mall.service.impl.ClassificationService;
import com.copyright.mall.util.UserUtils;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "后端分类查询")
@Slf4j
@RestController
@RequestMapping("/manage/v1/class")
public class ManageClassController {

    @Resource
    private IClassificationService classificationService;

    @PostMapping("/getClassAll")
    @ApiOperation("分类查询")
    public Wrapper<List<ManageClassResp>> getClassAll() {
        List<Classification> classifications = classificationService.getAll();
        List<ManageClassResp> manageClassResps = new ArrayList<>();
        classifications = classifications.stream().filter(classification -> classification.getMallId().equals(UserUtils.getMallId())).collect(Collectors.toList());
        classifications.forEach(classification -> {
            ManageClassResp manageClassResp = new ManageClassResp();
            BeanUtils.copyProperties(classification, manageClassResp);
            manageClassResps.add(manageClassResp);
        });
        return WrapMapper.ok(manageClassResps);
    }

}
