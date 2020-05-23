package com.copyright.mall.manage.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel("上下架商品参数")
public class UpDownGoodsParam {
    @ApiModelProperty("商品ID")
    private Long goodsId;
    @ApiModelProperty("商品是否上架（1上架，0下架）")
    private Integer itemStatus;
}
