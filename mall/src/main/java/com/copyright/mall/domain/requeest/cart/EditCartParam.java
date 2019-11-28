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

    @ApiModelProperty(value = "商品SKU",required = true)
    @NotBlank(message = "商品SKU不能为空")
    private String skuId;

    @ApiModelProperty(value = "修改数量 正数增加 负数减少 做和小于零直接删除",required = true)
    @NotNull(message = "修改数量不能为空")
    private Integer modifyCount;
}
