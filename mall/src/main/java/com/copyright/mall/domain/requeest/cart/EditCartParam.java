package com.copyright.mall.domain.requeest.cart;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author : zhangyuchen
 * @date : 2019/11/18 15:33
 */
@Data
@ApiModel
public class EditCartParam {

    @ApiModelProperty(value = "店铺编码",required = true)
    @NotBlank
    private String shopId;

    @ApiModelProperty(value = "商品SKU",required = true)
    @NotBlank
    private String skuId;

    @ApiModelProperty(value = "修改数量",required = true)
    @NotNull
    private Integer modifyCount;
}
