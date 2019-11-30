package com.copyright.mall.domain.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : zhangyuchen
 * @date : 2019/11/30 23:03
 */
@Data
@ApiModel
public class LoginInfoVO {
    @ApiModelProperty("用户Token")
    private String token;
}
