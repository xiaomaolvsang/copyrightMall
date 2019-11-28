package com.copyright.mall.controller.opus;

import com.copyright.mall.aspect.ControllerErro;
import com.copyright.mall.controller.BaseController;
import com.copyright.mall.domain.requeest.opus.OpusParam;
import com.copyright.mall.domain.requeest.product.ProductParam;
import com.copyright.mall.domain.vo.opus.OpusVO;
import com.copyright.mall.domain.vo.product.ProductVO;
import com.copyright.mall.service.impl.OpusService;
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

/**
 * OpusController
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/28 5:18 下午
 */
@RestController
@Api(tags = {"作品详情"})
@RequestMapping("/opus")
@Slf4j
public class OpusController extends BaseController {

  @Resource
  private OpusService opusService;

  @ApiOperation(value = "作品详情查询")
  @PostMapping("/opusDesc")
  @ControllerErro
  public Wrapper<OpusVO> productDesc(@RequestBody @ApiParam @Valid OpusParam opusParam){
    return WrapMapper.ok(opusService.getOpus(opusParam));
  }
}
