package com.copyright.mall.manage.domain.dto;


import com.copyright.mall.domain.dto.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("查询订单列表参数")
public class QueryOrderListParam extends BasePage {

    @ApiModelProperty("订单列表")
    private String orderNum;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("商家名")
    private String shopName;

    @ApiModelProperty("商家ID")
    private String shopId;

    @ApiModelProperty("订单开始时间")
    private Date orderStartTime;

    @ApiModelProperty("订单结束时间")
    private Date orderEndTime;

}
