package com.copyright.mall.domain.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author : zhangyuchen
 * @date : 2019/11/23 11:46
 */
@ApiModel
@Data
public class CreateAddressParam {
    @ApiModelProperty(value = "收货人姓名",required = true)
    @NotBlank(message = "收货人姓名不能为空")
    private String consigneeName;
    @ApiModelProperty(value = "收货人电话",required = true)
    @NotBlank(message = "收货人电话不能为空")
    private String consigneePhone;
    @ApiModelProperty(value = "省")
    private String province;
    @ApiModelProperty(value = "市")
    private String city;
    @ApiModelProperty(value = "区")
    private String area;
    @ApiModelProperty(value = "街道")
    private String street;
    @ApiModelProperty(value = "详情",required = true)
    @NotBlank(message = "详情不能为空")
    private String detail;
}
