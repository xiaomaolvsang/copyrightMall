package com.copyright.mall.controller.classification;

import com.copyright.mall.aspect.ControllerErro;
import com.copyright.mall.bean.resp.classification.ClassResp;
import com.copyright.mall.controller.BaseController;
import com.copyright.mall.domain.requeest.classification.ClassParam;
import com.copyright.mall.service.IClassificationService;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * ClassController
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/20 2:32 下午
 */
@RestController
@Api(tags = {"分类相关"})
@RequestMapping("/class")
@Slf4j
public class ClassController  extends BaseController {


  @Resource
  private IClassificationService classificationService;

  @ApiOperation(value = "一级分类查询")
  @PostMapping("/classOne")
  @ControllerErro
  public Wrapper<List<ClassResp>> test(@RequestBody @ApiParam @Valid ClassParam classParam){
    List<ClassResp> classResps = classificationService.getClassification(classParam);
    return WrapMapper.ok(classResps);
  }
}