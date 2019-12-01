package com.copyright.mall.domain.requeest.banner;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * ArtBanner
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/27 4:24 下午
 */
@Data
@ApiModel
public class ArtBannerParam {
  @ApiModelProperty("商城id")
  private Long mallId;
}
