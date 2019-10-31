package com.copyright.mall.domain.dto.test;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : zhangyuchen
 * @date : 2019/10/22 14:02
 */
@Data
@ApiModel("测试接口入参")
public class TestParam {

    @ApiModelProperty("测试ID")
    private String id;
}
