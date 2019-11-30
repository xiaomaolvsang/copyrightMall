package com.copyright.mall.domain.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author : zhangyuchen
 * @date : 2019/11/23 11:50
 */
@Data
@ApiModel
public class UserAddressDetailVO {
    @ApiModelProperty(value = "id",required = true)
    private String id;
    @ApiModelProperty(value = "收货人姓名",required = true)
    private String consigneeName;
    @ApiModelProperty(value = "收货人电话",required = true)
    private String consigneePhone;
    @ApiModelProperty(value = "省",required = true)
    private String province;
    @ApiModelProperty(value = "市",required = true)
    private String city;
    @ApiModelProperty(value = "区",required = true)
    private String area;
    @ApiModelProperty(value = "街道",required = true)
    private String street;
    @ApiModelProperty(value = "详情",required = true)
    @NotBlank(message = "详情不能为空")
    private String detail;
}
