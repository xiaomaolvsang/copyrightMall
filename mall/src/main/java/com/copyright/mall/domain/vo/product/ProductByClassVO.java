package com.copyright.mall.domain.vo.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * ProductByClass
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/25 8:00 下午
 */
@ApiModel
@Data
public class ProductByClassVO {
  @ApiModelProperty("商品信息")
  private List<AreaVO.AreaAttr> data;
}
