package com.copyright.mall.manage.domain.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Api("根据phone获取用户参数")
@Data
public class GetUserByPhoneParam {

    @ApiModelProperty("电话号码")
    @NotBlank(message = "电话号码不能为空")
    private String phone;
}
