package com.copyright.mall.controller.banner;

import com.copyright.mall.controller.BaseController;
import com.copyright.mall.domain.dto.order.ConfirmOrderParam;
import com.copyright.mall.domain.requeest.banner.ArtBannerParam;
import com.copyright.mall.domain.requeest.banner.BannerParam;
import com.copyright.mall.domain.vo.banner.ArtBannerVO;
import com.copyright.mall.domain.vo.banner.BannerVO;
import com.copyright.mall.domain.vo.order.ConfirmOrderVO;
import com.copyright.mall.service.IBannerService;
import com.copyright.mall.service.impl.BannerService;
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
 * BannerController
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/23 6:26 下午
 */
@RestController
@Api(tags = {"首页组件"})
@RequestMapping("/banner")
@Slf4j
public class BannerController extends BaseController {

  @Resource
  private IBannerService  bannerService;

  @PostMapping("/bannerSearch")
  @ApiOperation("首页组件查询")
  public Wrapper<BannerVO> bannerSearch(@ApiParam @Valid @RequestBody BannerParam bannerParam){
    bannerParam.setMallId(getMallId());
    return WrapMapper.ok(bannerService.getBanner(bannerParam));
  }

  @PostMapping("/artBanner")
  @ApiOperation("艺术家组件查询")
  public Wrapper<ArtBannerVO> artBanner(@ApiParam @Valid @RequestBody ArtBannerParam artBannerParam){
    artBannerParam.setMallId(getMallId());
    return WrapMapper.ok(bannerService.getArtBanner(artBannerParam));
  }
}
