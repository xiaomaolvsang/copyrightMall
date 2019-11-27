package com.copyright.mall.domain.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author : zhangyuchen
 * @date : 2019/11/27 16:13
 */
@Data
@ApiModel
public class PayDTO {

    @ApiModelProperty(value = "商城订单号")
    @NotBlank
    private String mallOrderID;
}
