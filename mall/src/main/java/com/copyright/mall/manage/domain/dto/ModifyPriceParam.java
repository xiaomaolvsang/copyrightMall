package com.copyright.mall.manage.domain.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@ApiModel("修改订单价格")
public class ModifyPriceParam {

    @ApiModelProperty(value = "订单ID",required = true)
    @NotBlank(message = "订单号不能为空")
    private String orderId;

    @ApiModelProperty(value = "商品ID",required = true)
    @NotBlank(message = "商品ID不能为空")
    private String itemId;

    @ApiModelProperty(value = "商品价格",required = true)
    @Min(value = 0,message = "商品价格不能小于0")
    private Double itemPrice;
}
