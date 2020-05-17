package com.copyright.mall.manage.domain.dto;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class ShopOrderDetail {

    @ApiModelProperty("店铺ID")
    private String shopId;
    @ApiModelProperty("店铺名称")
    private String shopName;
    @ApiModelProperty("订单状态")
    private String orderStatus;
    @ApiModelProperty("支付金额")
    private Integer payPrice;
    @ApiModelProperty("订单ID")
    private String shopOrderId;
    @ApiModelProperty("快递公司")
    private String delliveryCompanyName;
    @ApiModelProperty("运单号")
    private String deliveryOrderId;
    @ApiModelProperty("创建时间")
    private String createTime;
    @ApiModelProperty("支付时间")
    private Date payTime;
    @ApiModelProperty("ItemOrder列表")
    private List<ItemOrder> itemOrders;

    @Data
    public static class ItemOrder{
        @JSONField(name = "image")
        @ApiModelProperty("商品图片")
        private String image;
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
        @JSONField(name = "itemType")
        @ApiModelProperty("itemType")
        private int itemType;
        @ApiModelProperty("虚拟商品")
        private boolean virtual;

    }
}
