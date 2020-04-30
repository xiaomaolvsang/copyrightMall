package com.copyright.mall.manage.domain.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Api("导出订单参数")
@Data
public class ExportOrderParam {


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