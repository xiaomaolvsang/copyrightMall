package com.copyright.mall.controller.detail;

import com.copyright.mall.aspect.ControllerErro;
import com.copyright.mall.bean.resp.product.ProductSearchResp;
import com.copyright.mall.controller.BaseController;
import com.copyright.mall.domain.requeest.detail.DetailParam;
import com.copyright.mall.domain.requeest.product.ProductSearchParam;
import com.copyright.mall.domain.vo.details.DetailVO;
import com.copyright.mall.service.impl.DetailService;
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
 * DetailController
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/28 1:17 下午
 */
@RestController
@Api(tags = {"详情相关"})
@RequestMapping("/detail")
@Slf4j
public class DetailController extends BaseController {

  @Resource
  private DetailService detailService;
  @ApiOperation(value = "详情搜索")
  @PostMapping("/search")
  @ControllerErro
  public Wrapper<DetailVO> search(@RequestBody @ApiParam @Valid DetailParam detailParam){
    detailParam.setMallId(getMallId());
    detailParam.setUserId(getUserId());
    return WrapMapper.ok(detailService.getDetail(detailParam));
  }

}
