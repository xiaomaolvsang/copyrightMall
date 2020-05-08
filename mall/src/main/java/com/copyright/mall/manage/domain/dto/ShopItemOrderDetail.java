package com.copyright.mall.manage.domain.dto;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
public class ShopItemOrderDetail {

    @JSONField(name = "shopId")
    @ApiModelProperty("店铺ID")
    private String shopId;
    @JSONField(name = "shopName")
    @ApiModelProperty("店铺名称")
    private String shopName;
    @JSONField(name = "image")
    @ApiModelProperty("商品图片")
    private String image;
    @JSONField(name = "status")
    @ApiModelProperty("订单状态")
    private String orderStatus;
    @ApiModelProperty("支付金额")
    private Integer payPrice;
    @JSONField(name = "shopOrderId")
    @ApiModelProperty("订单ID")
    private String shopOrderId;
    @ApiModelProperty("itemOrdreID")
    private String itemOrderId;
    @JSONField(name = "productName")
    @ApiModelProperty("商品名")
    private String productName;
    @JSONField(name = "productPrice")
    @ApiModelProperty("商品售价")
    private Integer productPrice;
    @JSONField(name = "productId")
    @ApiModelProperty("skuId")
    private String skuId;
    @JSONField(name = "num")
    @ApiModelProperty("数量")
    private int num;
}
