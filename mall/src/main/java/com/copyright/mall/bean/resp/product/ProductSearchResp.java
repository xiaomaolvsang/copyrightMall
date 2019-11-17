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
  private Integer productPrice;
  @ApiModelProperty("productId")
  private Long productId;


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public Integer getProductPrice() {
    return productPrice;
  }

  public void setProductPrice(Integer productPrice) {
    this.productPrice = productPrice;
  }

  public Long getShoID() {
    return shoID;
  }

  public void setShoID(Long shoID) {
    this.shoID = shoID;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }
}
