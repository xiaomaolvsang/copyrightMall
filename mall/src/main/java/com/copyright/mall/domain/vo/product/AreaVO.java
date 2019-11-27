package com.copyright.mall.domain.vo.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * AreaVO
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/24 3:53 下午
 */
@ApiModel
@Data
public class AreaVO {

  @ApiModelProperty("类型 mall/copyright")
  private String type;
  private List<AreaAttr> areaAttrs;

  @Data
  public static class AreaAttr{
    @ApiModelProperty("图片")
    private String image;
    @ApiModelProperty("商铺名称")
    private String shopName;
    @ApiModelProperty("商铺id")
    private Long shopID;
    @ApiModelProperty("商品名称")
    private String productName;
    @ApiModelProperty("商品价格")
    private String productPrice;
    @ApiModelProperty("商品id")
    private String productId;
  }
}
