package com.copyright.mall.domain.dto.copyright;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel
public class CopyrightEevokeParam {

    @ApiModelProperty(value = "版权ID",required = true)
    @NotBlank(message = "版权ID不能为空")
    String certificateId;
}
