package com.copyright.mall.manage.domain.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Api("审核参数")
@Data
public class CertificateParam {

    @ApiModelProperty("审核类型 copyright 版权 certificate 证书")
    @NotBlank(message = "审核类型不能为空")
    private String type;

    @ApiModelProperty("目标状态 0 通过 1 驳回")
    @NotNull(message ="目标状态不能为空 ")
    private Integer status;

    @ApiModelProperty("审核ID")
    @NotEmpty(message ="审核ID不能为空")
    private String id;
}
