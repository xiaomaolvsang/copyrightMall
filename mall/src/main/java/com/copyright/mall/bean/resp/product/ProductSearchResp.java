package com.copyright.mall.bean.resp.product;

import com.copyright.mall.bean.page.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * ProductSearchResp
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/17 2:37 下午
 */
@Data
@ApiModel("product搜索出参")
public class ProductSearchResp implements Serializable{

  @ApiModelProperty("类型")
  private String type;
  @ApiModelProperty("缩略图")
  private String avatar;
  @ApiModelProperty("图片url")
  private String image;
  @ApiModelProperty("商铺名称")
  private String shopName;
  @ApiModelProperty("商铺id")
  private Long shoID;
  @ApiModelProperty("product名称")
  private String productName;
  @ApiModelProperty("product价格(单位：分)")
  private String productPrice;
  @ApiModelProperty("product划线价(单位：分)")
  private String productUnderlinedPrice;
  @ApiModelProperty("product库存")
  private String productInventory;
  @ApiModelProperty("productId")
  private Long productId;
  @ApiModelProperty("行业")
  private String artCategory;
}
