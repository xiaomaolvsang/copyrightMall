package com.copyright.mall.domain.dto.copyright;

import com.copyright.mall.domain.dto.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CopyrightQueryParam extends BasePage {

    @ApiModelProperty(value = "状态 10待审核 20已驳回 30已确权 40已授权")
    private int status;
}
