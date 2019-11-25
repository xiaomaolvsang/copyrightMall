package com.copyright.mall.domain.requeest.banner;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * BannerReq
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/23 6:28 下午
 */
@Data
@ApiModel
public class BannerParam{
  @ApiModelProperty("商城与版权（mall/copyright）")
  @NotBlank(message = "商城与版权不能为空")
  private String type;
  @ApiModelProperty("商城id")
  @NotNull(message = "mallId不能为空")
  private Long mallId;
}
