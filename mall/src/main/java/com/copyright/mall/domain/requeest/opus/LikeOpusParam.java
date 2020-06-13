package com.copyright.mall.domain.requeest.opus;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LikeOpusParam {
    @ApiModelProperty("作品id")
    private Long id;
    @ApiModelProperty("我自己取")
    private Long userId;
}
