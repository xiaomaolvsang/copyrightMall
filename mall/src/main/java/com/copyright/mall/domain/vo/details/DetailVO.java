package com.copyright.mall.domain.vo.details;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * DetailVO
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/28 11:31 上午
 */
@ApiModel
@Data
public class DetailVO {
  @ApiModelProperty("属性")
  private DetailData data;
  @ApiModelProperty("作品")
  private List<Opus> opus;
  @ApiModelProperty("商品")
  private List<Products> products;

  @Data
  @ApiModel
  public static class DetailData{

    @ApiModelProperty("艺术家详情页")
    private String avatar;
    @ApiModelProperty("艺术家详情页姓名")
    private String artistName;
    @ApiModelProperty("艺术家详情页")
    private String artCategory;
    @ApiModelProperty("艺术家详情页海报")
    private String posterPic;
    @ApiModelProperty("艺术家详情页Id")
    private Long artistId;
    @ApiModelProperty("艺术家详情页")
    private String artIntroduction;

    @ApiModelProperty("机构图片")
    private String organizationImage;
    @ApiModelProperty("机构名称")
    private String organizationName;
    @ApiModelProperty("机构")
    private String isIdentification;

    @ApiModelProperty("作品图片")
    private String opusIcon;
    @ApiModelProperty("作品名称")
    private String opusName;
    @ApiModelProperty("作品详情")
    private String connectInfo;
    @ApiModelProperty("作品")
    private List<Images> introduction;
  }

  @Data
  @ApiModel
  public static class Opus{
    private String image;
    private String name;
    private Long opusId;

    public String getImage() {
      return image;
    }

    public void setImage(String image) {
      this.image = image;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Long getOpusId() {
      return opusId;
    }

    public void setOpusId(Long opusId) {
      this.opusId = opusId;
    }
  }
  @Data
  @ApiModel
  public static class Products{
    private String image;
    private String shopName;
    private Long shopID;
    private String productName;
    private String productPrice;
    private Long productId;
  }
  @Data
  public static class Images{
    private String image;
  }
}
