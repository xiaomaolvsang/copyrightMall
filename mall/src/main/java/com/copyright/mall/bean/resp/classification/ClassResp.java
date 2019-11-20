package com.copyright.mall.bean.resp.classification;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassResp
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/20 2:41 下午
 */
@Data
@ApiModel("product搜索出参")
public class ClassResp {
  @ApiModelProperty("一级分类名称")
  private String firstCategoryName;
  @ApiModelProperty("一级分类Id")
  private Long firstCategoryId;
}
