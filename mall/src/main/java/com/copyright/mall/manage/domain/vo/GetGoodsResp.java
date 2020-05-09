package com.copyright.mall.manage.domain.vo;

import com.copyright.mall.domain.dto.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("商品查询返回参数")
public class GetGoodsResp extends BasePage {

    @ApiModelProperty("商品类型(0 product/ 1 artist)")
    private Integer type;
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
    @ApiModelProperty("productId")
    private Long productId;
    @ApiModelProperty("行业")
    private String artCategory;
    @ApiModelProperty("分类")
    private List<ClassLevel> classLevel;
    @ApiModelProperty("上下架（1上架，0下架）")
    private Integer itemStatus;

    private List<SkuResp> skuResps;

    private List<Opus> opuses;

    @Data
    @ApiModel("分类返回参数")
    public static class ClassLevel{
        @ApiModelProperty("子分类")
        private ClassLevel son;
        @ApiModelProperty("分类名")
        private String classname;
        @ApiModelProperty("分类ID")
        private Long classId;
    }


    @Data
    @ApiModel("sku返回参数")
    public static class SkuResp{
        @ApiModelProperty("价格")
        private Integer price;
        @ApiModelProperty("尺寸")
        private String sizeKey;
        @ApiModelProperty("尺寸值")
        private String sizeValue;
    }

    @Data
    @ApiModel("作品参数")
    public static class Opus{
        @ApiModelProperty("头图")
        private String image;
        @ApiModelProperty("id")
        private Long opusId;
        @ApiModelProperty("作品名称")
        private String opusName;
        @ApiModelProperty("作品标题")
        private String opusTitle;
        @ApiModelProperty("作品详情")
        private String opusDesc;
        @ApiModelProperty("作品图片（用逗号隔开）")
        private String imgs;
    }


}
