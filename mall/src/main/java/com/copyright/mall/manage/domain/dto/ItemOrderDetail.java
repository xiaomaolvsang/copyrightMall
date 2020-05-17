package com.copyright.mall.manage.domain.dto;


import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ItemOrderDetail {

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
    @ApiModelProperty("itenm类型")
    private int itemType;
}
