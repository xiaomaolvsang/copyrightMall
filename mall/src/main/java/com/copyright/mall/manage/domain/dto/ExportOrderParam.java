package com.copyright.mall.manage.domain.dto;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Api("导出订单参数")
@Data
public class ExportOrderParam {


    @ApiModelProperty("订单号")
    private String orderId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("商家名")
    private String shopName;

    @ApiModelProperty("商家ID")
    private String shopId;

    @ApiModelProperty("订单开始时间")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderStartTime;

    @ApiModelProperty("订单结束时间")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderEndTime;


    @ApiModelProperty("商品名称")
    private String itemName;
}
