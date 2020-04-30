package com.copyright.mall.manage.domain.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@ApiModel("修改订单价格")
public class ShipParam {

    @ApiModelProperty(value = "订单ID",required = true)
    @NotBlank(message = "订单号不能为空")
    private String orderId;

    @ApiModelProperty(value = "快递公司名称",required = true)
    @NotBlank(message = "快递公司名称不能为空")
    private String companyName;

    @ApiModelProperty(value = "快递单号",required = true)
    @NotBlank(message = "快递单号不能为空")
    private Double trackingNumber;
}
