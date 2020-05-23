package com.copyright.mall.manage.controller;


import com.copyright.mall.bean.Classification;
import com.copyright.mall.bean.resp.classification.ManageClassResp;
import com.copyright.mall.manage.domain.vo.GetGoodsResp;
import com.copyright.mall.service.IClassificationService;
import com.copyright.mall.service.impl.ClassificationService;
import com.copyright.mall.service.impl.product.ProductServiceImpl;
import com.copyright.mall.util.UserUtils;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api(tags = "后端分类查询")
@Slf4j
@RestController
@RequestMapping("/manage/v1/class")
public class ManageClassController {

    @Resource
    private IClassificationService classificationService;

    @Resource
    private ProductServiceImpl productService;

    @PostMapping("/getClassAll")
    @ApiOperation("分类查询")
    public Wrapper<List<ManageClassResp>> getClassAll() {
        List<Classification> classifications = classificationService.getAll();
        List<ManageClassResp> manageClassResps = new ArrayList<>();
        classifications = classifications.stream().filter(classification -> classification.getMallId().equals(UserUtils.getMallId())).collect(Collectors.toList());

        List<Classification> classLevelOne = classifications.stream().filter(classification -> classification.getUpperId() == 0L).collect(Collectors.toList());
        Map<Long, List<Classification>> map = classifications.stream().collect(Collectors.groupingBy(Classification::getUpperId));

        classLevelOne.forEach(classification -> {
            ManageClassResp manageClassResp = new ManageClassResp();
            manageClassResp.setClassName(classification.getClassName());
            manageClassResp.setId(classification.getId());
            manageClassResp.setMallId(classification.getMallId());
            manageClassResp.setUpperId(classification.getUpperId());
            List<ManageClassResp> manageClassResps1 = new ArrayList<>();
            if(!CollectionUtils.isEmpty(map.get(manageClassResp.getId()))){
                List<Classification> classifications1 = map.get(manageClassResp.getId());
                classifications1.forEach(classification1 -> {
                    ManageClassResp manageClassResp1 = new ManageClassResp();
                    manageClassResp1.setClassName(classification1.getClassName());
                    manageClassResp1.setId(classification1.getId());
                    manageClassResp1.setMallId(classification1.getMallId());
                    manageClassResp1.setUpperId(classification1.getUpperId());
                    manageClassResps1.add(manageClassResp1);
                });
            }
            manageClassResp.setSon(manageClassResps1);
            manageClassResps.add(manageClassResp);
        });

        return WrapMapper.ok(manageClassResps);
    }

}
