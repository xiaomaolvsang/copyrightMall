package com.copyright.mall.domain.requeest.classification;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel
public class ClassByShopParam {
    @ApiModelProperty(value = "商铺编码",required = true)
    private Long shopId;
}
