package com.copyright.mall.manage.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("删除作品参数")
public class OpusDelParam {
    @ApiModelProperty("id")
    @NotNull(message = "id不能为空")
    private Long id;
}
