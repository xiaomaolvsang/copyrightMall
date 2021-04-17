package com.copyright.mall.manage.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("查询商品列表参数")
public class UpGoodsParam {
    @ApiModelProperty("商铺id")
    private Long shopId;
    @ApiModelProperty("商品详情")
    private GoodsParam goodsParam;

    @Data
    @ApiModel("商品详情")
    public static class GoodsParam{
        @ApiModelProperty("商品ID")
        private Long goodsId;
        @ApiModelProperty("分类id")
        private List<Long> itemClassIds;
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
        @ApiModelProperty("sku信息")
        private List<Sku> skus;
        @ApiModelProperty("艺术作品详情")
        private List<ArtOps> artOps;
        @ApiModelProperty("艺术类别")
        private String artCategory;
        @ApiModelProperty("虚拟商品类型 （0正常，1虚拟）")
        private Integer itemType;
    }

    @Data
    @ApiModel("sku详情")
    public static class Sku{
        @ApiModelProperty("skuId")
        private Long skuId;
        @ApiModelProperty("商品价格（单位：分）")
        private Integer price;
        @ApiModelProperty("product划线价(单位：分)")
        private Integer productUnderlinedPrice;
        @ApiModelProperty("product库存")
        private Integer productInventory;
        @ApiModelProperty("商品尺寸")
        private String sizeKey;
        @ApiModelProperty("商品尺寸值")
        private String sizeValue;
    }

    @Data
    @ApiModel("作品详情")
    public static class ArtOps{
        @ApiModelProperty("商品id")
        private Long itemId;
        @ApiModelProperty("作品名称")
        private String artOpsName;
        @ApiModelProperty("作品标题")
        private String artOpTitle;
        @ApiModelProperty("作品详情")
        private String opusDesc;
        @ApiModelProperty("作品图片")
        private List<String> imgs;
        @ApiModelProperty("作品头图")
        private String image;
        @ApiModelProperty("作品id")
        private Long opusId;
    }
}


