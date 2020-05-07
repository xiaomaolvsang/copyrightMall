package com.copyright.mall.manage.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("登陆返回参数")
public class LoginRes {
    @ApiModelProperty("登陆返回成功码")
    private String errorCode;
    @ApiModelProperty("登陆返回信息")
    private String errorMsg;
}
