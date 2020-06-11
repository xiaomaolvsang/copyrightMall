package com.copyright.mall.domain.requeest.opus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("作品删除")
public class DeleteOpusParam {
    @ApiModelProperty("作品id")
    private Long id;
    @ApiModelProperty("我自己取")
    private Long userId;
}
