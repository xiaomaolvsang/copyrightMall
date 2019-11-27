package com.copyright.mall.domain.requeest.classification;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * ClassTwoParam
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/25 7:38 下午
 */
@Data
@ApiModel
public class ClassTwoParam {
  @ApiModelProperty(value = "一级分类",required = true)
  @NotNull(message = "一级分类不能为空")
  private Long classOneId;
}
