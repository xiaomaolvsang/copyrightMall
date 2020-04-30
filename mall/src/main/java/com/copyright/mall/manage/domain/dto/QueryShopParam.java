package com.copyright.mall.manage.domain.dto;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Api("商铺查询接口参数")
@Data
public class QueryShopParam {
    @ApiModelProperty("userId")
    private String userId;
}
