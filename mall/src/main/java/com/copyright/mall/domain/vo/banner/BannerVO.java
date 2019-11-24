package com.copyright.mall.domain.vo.banner;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * bannerVO
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/23 6:32 下午
 */
@Data
@ApiModel("首页组件")
public class BannerVO {

  @ApiModelProperty("组建列表")
  @JSONField(name = "data")
  private List<BannerList> data;

  @Data
  @ApiModel("组建列表")
  public static class BannerList {
    @ApiModelProperty("组建类型")
    private String type;
    @ApiModelProperty("组建高度")
    private String height;
    @ApiModelProperty("组建宽度")
    private String width;
    @ApiModelProperty("组件属性")
    @JSONField(name = "data")
    private List<Attributes> data;
    @ApiModelProperty("组建商品列表")
    @JSONField(name = "products")
    private List<Products> products;
  }

  @Data
  @ApiModel("组建属性")
  public static class Attributes {
    @ApiModelProperty("图片")
    private String image;
    @ApiModelProperty("")
    private String linkType;
    @ApiModelProperty("")
    private String targetUrl;
    @ApiModelProperty("")
    private String categoryName;
    @ApiModelProperty("")
    private String categoryId;
    @ApiModelProperty("标题")
    private String title;

  }

  @Data
  @ApiModel("组建商品")
  public static class Products {
    @ApiModelProperty("商品id")
    private Long productId;
    @ApiModelProperty("商品图片")
    private String productImage;
    @ApiModelProperty("商品名称")
    private String productName;
    @ApiModelProperty("")
    private String linkType;
    @ApiModelProperty("")
    private String targetUrl;
  }
}
