package com.copyright.mall.domain.vo.banner;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * ArtBannerVO
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/27 4:31 下午
 */
@Data
@ApiModel("艺术家组件")
public class ArtBannerVO {
  @ApiModelProperty("组建列表")
  @JSONField(name = "data")
  private List<ArtBannerVO.BannerList> data;


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
    private List<BannerVO.Attributes> data;
    @ApiModelProperty("组件艺术家列表")
    @JSONField(name = "artist")
    private List<ArtBannerVO.Artists> artist;
  }

  @Data
  @ApiModel("艺术家")
  public static class Artists {
    @ApiModelProperty("艺术家id")
    private Long artistId;
    @ApiModelProperty("")
    private String avatar;
    @ApiModelProperty("艺术家名称名称")
    private String artistName;
    @ApiModelProperty("艺术类别")
    private String artCategory;
    @ApiModelProperty("")
    private String posterPic;
  }
}
