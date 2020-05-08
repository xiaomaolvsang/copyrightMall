package com.copyright.mall.manage.domain.dto;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class ShopItemOrderDetail {

    @ApiModelProperty("店铺ID")
    private String shopId;
    @ApiModelProperty("店铺名称")
    private String shopName;
    @ApiModelProperty("商品图片")
    private String image;
    @ApiModelProperty("订单状态")
    private String orderStatus;
    @ApiModelProperty("支付金额")
    private Integer payPrice;
    @ApiModelProperty("订单ID")
    private String shopOrderId;
    @ApiModelProperty("itemOrdreID")
    private String itemOrderId;
    @ApiModelProperty("商品名")
    private String productName;
    @ApiModelProperty("商品售价")
    private Integer productPrice;
    @ApiModelProperty("skuId")
    private String skuId;
    @ApiModelProperty("数量")
    private int num;
    @ApiModelProperty("快递公司")
    private String delliveryCompanyName;
    @ApiModelProperty("运单号")
    private String deliveryOrderId;
    @ApiModelProperty("创建时间")
    private String createTime;
    @ApiModelProperty("支付时间")
    private Date payTime;
}
