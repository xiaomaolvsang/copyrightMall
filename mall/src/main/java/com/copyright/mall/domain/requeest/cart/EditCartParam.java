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
    @NotBlank(message = "店铺编码不能为空")
    private String shopId;

    @ApiModelProperty(value = "商品SKU",required = true)
    @NotBlank(message = "商品SKU不能为空")
    private String skuId;

    @ApiModelProperty(value = "修改数量",required = true)
    @NotNull(message = "修改数量不能为空")
    private Integer modifyCount;
}
