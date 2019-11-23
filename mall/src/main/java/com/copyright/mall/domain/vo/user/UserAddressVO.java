package com.copyright.mall.domain.vo.user;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author : zhangyuchen
 * @date : 2019/11/23 11:50
 */
@Data
@ApiModel
public class UserAddressVO {
    @JSONField(name = "consigneeName")
    @ApiModelProperty("收货人姓名")
    @NotBlank
    private String consigneeName;
    @JSONField(name = "consigneePhone")
    @ApiModelProperty("收货人电话")
    @NotBlank
    private String consigneePnone;
    @JSONField(name = "address")
    @ApiModelProperty("收货地址 待确定 标准省市区？")
    @NotBlank
    private String address;
}
