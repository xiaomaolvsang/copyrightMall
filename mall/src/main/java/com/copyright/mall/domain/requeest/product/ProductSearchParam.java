package com.copyright.mall.domain.requeest.product;

import com.copyright.mall.bean.page.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * ProductParam
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/17 2:30 下午
 */
@Data
@ApiModel("product搜索入参")
public class ProductSearchParam extends Page {

  @ApiModelProperty(value = "商城id", required = true)
  @NotNull(message = "商城id不能为空")
  private Long mallId;

  @ApiModelProperty(value = "搜索关键字", required = true)
  private String keyword;

  @ApiModelProperty(value = "搜索类型（product/artist）", required = true)
  @NotBlank(message = "搜索类型不能为空")
  private String type;
}
