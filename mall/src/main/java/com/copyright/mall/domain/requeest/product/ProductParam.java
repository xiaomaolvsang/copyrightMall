package com.copyright.mall.domain.requeest.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * ProdcutParam
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/28 3:41 下午
 */
@Data
@ApiModel("商品详情查询入参")
public class ProductParam {
  @ApiModelProperty("商城Id")
  private Long mallId;
  @ApiModelProperty("商品id")
  @NotNull(message = "商品id不能为空")
  private Long productId;
}
