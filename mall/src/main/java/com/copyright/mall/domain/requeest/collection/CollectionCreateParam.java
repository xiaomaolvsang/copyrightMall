package com.copyright.mall.domain.requeest.collection;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("收藏新建参数")
public class CollectionCreateParam {
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("作品id")
    private Long opusId;
}
