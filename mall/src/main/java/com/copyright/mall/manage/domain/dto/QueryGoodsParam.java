package com.copyright.mall.manage.domain.dto;


import com.copyright.mall.domain.dto.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("查询商品参数")
public class QueryGoodsParam extends BasePage {
    @ApiModelProperty("商品号")
    private String barCode;
    @ApiModelProperty("一级分类")
    private String classLevelFirst;
    @ApiModelProperty("二级分类")
    private String classLevelSecond;
    @ApiModelProperty("商品名称")
    private String itemTitle;
    @ApiModelProperty("商铺号")
    private Long ShopId;
    @ApiModelProperty("商品ID")
    private Long goodsId;
    @ApiModelProperty("商品是否上架（1上架，0下架）")
    private Integer itemStatus;
    @ApiModelProperty("商城ID")
    private Long mallId;
    @ApiModelProperty("商品类型(product/artist)")
    private String type;
}
