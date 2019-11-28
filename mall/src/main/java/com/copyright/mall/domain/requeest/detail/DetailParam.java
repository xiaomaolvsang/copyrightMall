package com.copyright.mall.domain.requeest.detail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * DetailParam
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/28 1:19 下午
 */
@Data
@ApiModel("DetailParam入参")
public class DetailParam {
  @ApiModelProperty("商城Id")
  @NotNull(message = "商城Id不能为空")
  private Long mallId;
  @ApiModelProperty("数据id")
  private Long dataId;
  @ApiModelProperty("详情类型 artist/mall/copyright")
  @NotBlank(message = "详情类型不能为空")
  private String type;
  @ApiModelProperty("商铺id")
  private Long shopId;
}
