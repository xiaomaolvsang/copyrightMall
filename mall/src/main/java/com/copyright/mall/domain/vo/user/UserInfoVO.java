package com.copyright.mall.domain.vo.user;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : zhangyuchen
 * @date : 2019/11/23 11:51
 */
@Data
@ApiModel
public class UserInfoVO {

    @JSONField(name = "avatar")
    @ApiModelProperty("头像")
    private String avatar;
    @JSONField(name = "name")
    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("电话")
    private String phone;
}
