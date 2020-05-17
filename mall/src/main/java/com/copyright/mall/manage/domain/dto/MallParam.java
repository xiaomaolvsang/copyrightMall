package com.copyright.mall.manage.domain.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Api("商城参数")
@Data
public class MallParam {
    @ApiModelProperty("商铺id")
    private Long shopId;
}
