package com.copyright.mall.manage.domain.vo;

import com.copyright.mall.domain.dto.BasePage;
import io.swagger.annotations.ApiModelProperty;

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
    @ApiModelProperty("product价格(单位：分)")
    private String productPrice;
    @ApiModelProperty("productId")
    private Long productId;
    @ApiModelProperty("行业")
    private String artCategory;
    @ApiModelProperty("分类")
    private String classLevel;
    @ApiModelProperty("上下架（1上架，0下架）")
    private String itemStatus;

}
