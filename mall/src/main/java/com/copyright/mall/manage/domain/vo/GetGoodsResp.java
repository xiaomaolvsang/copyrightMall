package com.copyright.mall.manage.domain.vo;

import com.copyright.mall.domain.dto.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("商品查询返回参数")
public class GetGoodsResp extends BasePage {

    @ApiModelProperty("商品类型(product/artist)")
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
    @ApiModelProperty("productId")
    private Long productId;
    @ApiModelProperty("行业")
    private String artCategory;
    @ApiModelProperty("分类")
    private String classLevel;
    @ApiModelProperty("上下架（1上架，0下架）")
    private Integer itemStatus;

    private List<SkuResp> skuResps;
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

}
