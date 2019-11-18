package com.copyright.mall.domain.dto.test;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author : zhangyuchen
 * @date : 2019/10/22 14:02
 */
@Data
@ApiModel("测试接口入参")
public class TestParam {

    @ApiModelProperty(value = "测试ID",required = true)
    @NotBlank(message = "测试ID不能为空")
    private String id;

    @ApiModelProperty(value = "测试Name",required = true)
    @NotBlank(message = "测试Name不能为空")
    private String name;
}
