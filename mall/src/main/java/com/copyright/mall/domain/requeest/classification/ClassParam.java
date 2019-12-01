package com.copyright.mall.domain.requeest.classification;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * ClassParam
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/20 2:34 下午
 */

@Data
@ApiModel
public class ClassParam {

  @ApiModelProperty(value = "商城编码",required = true)
  private Long mallId;

}
