package com.copyright.mall.manage.domain.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Api("登陆参数")
@Data
public class LoginParam {
    @ApiModelProperty("电话")
    @NotBlank(message = "电话不能为空")
    private String phone;
    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    private String password;
}
