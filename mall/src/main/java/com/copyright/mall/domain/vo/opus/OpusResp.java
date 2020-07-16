package com.copyright.mall.domain.vo.opus;

import com.copyright.mall.domain.dto.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("作品列表")
public class OpusResp extends BasePage {
    @ApiModelProperty("作平图片")
    private String opusImg;
    @ApiModelProperty("作品标题")
    private String opusTitle;
    @ApiModelProperty("艺术家头像")
    private String artHeadPortrait;
    @ApiModelProperty("艺术家名称")
    private String artName;
    @ApiModelProperty("点赞数")
    private Long likesNum;
    @ApiModelProperty("id")
    private Long opusId;
    @ApiModelProperty("作品详情")
    private String opusDesc;
    @ApiModelProperty("作品图片集")
    private List<String> opusImgs;
    @ApiModelProperty("相关商品")
    private OpusGoods opusGoods;
    @ApiModelProperty("是否收藏 true(是)")
    private Boolean ifCollection;

    @Data
    public static class OpusGoods{
        @ApiModelProperty("商品图片")
        private String goodsImg;
        @ApiModelProperty("商品名称")
        private String goodsName;
        @ApiModelProperty("店铺名称")
        private String shopName;
        @ApiModelProperty("价格")
        private int price;
    }
}
