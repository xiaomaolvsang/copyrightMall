package com.copyright.mall.manage.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("查询订单列表参数")
public class UpGoodsParam {
    @ApiModelProperty("商铺id")
    private String shopId;
    @ApiModelProperty("商品详情")
    private GoodsParam goodsParam;
    @ApiModelProperty("艺术作品详情")
    private ArtOps artOps;
    @ApiModelProperty("商品ID")
    private String goodsId;


    public static class GoodsParam{
        @ApiModelProperty("分类id")
        private String itemClassId;
        @ApiModelProperty("商标号")
        private String copyRightId;
        @ApiModelProperty("商品名称")
        private String itemTitle;
        @ApiModelProperty("广告")
        private String ad;
        @ApiModelProperty("商品标题图片")
        private String titleImg;
        @ApiModelProperty("商品详情图片")
        private List<String> contentImg;
        @ApiModelProperty("商品价格（单位：分）")
        private Integer price;
        @ApiModelProperty("商品尺寸")
        private String sizeKey;
        @ApiModelProperty("商品尺寸值")
        private String sizeValue;
    }

    public static class ArtOps{
        @ApiModelProperty("商品id")
        private String itemId;
        @ApiModelProperty("作品名称")
        private String artOpsName;
        @ApiModelProperty("作品标题")
        private String artOpTitle;
        @ApiModelProperty("作品详情")
        private String opusDesc;
        @ApiModelProperty("作品图片")
        private String imgs;
        @ApiModelProperty("作品头图")
        private String image;
    }
}


