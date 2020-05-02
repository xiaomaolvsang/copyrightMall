package com.copyright.mall.manage.domain.dto;


import com.copyright.mall.domain.dto.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("查询订单列表参数")
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
    private String ShopId;
}
