package com.copyright.mall.domain.requeest.opus;

import com.copyright.mall.domain.dto.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("作品列表入参")
public class OpusReq extends BasePage {
    @ApiModelProperty("我自己取")
    private Long userId;
}
