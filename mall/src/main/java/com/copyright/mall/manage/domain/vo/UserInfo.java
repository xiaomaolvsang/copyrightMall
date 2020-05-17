package com.copyright.mall.manage.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel
@Data
public class UserInfo {

    @ApiModelProperty("店铺名")
    private String shopName;

    @ApiModelProperty("权限列表  1管理员 ")
    private List<Long> roleIds;

    @ApiModelProperty("/电话")
    private String mobile;

    @ApiModelProperty("mallId")
    private Long mallId;

}
