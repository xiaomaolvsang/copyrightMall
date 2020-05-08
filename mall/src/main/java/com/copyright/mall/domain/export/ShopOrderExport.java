package com.copyright.mall.domain.export;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShopOrderExport {

    @ExcelProperty(value = "店铺ID",index = 0)
    private String shopId;
    @ExcelProperty(value = "店铺名称",index = 1)
    private String shopName;
    @ExcelProperty(value = "订单状态",index = 2)
    private String orderStatus;
    @ExcelProperty(value = "订单状态枚举",index = 3)
    private String statusDesc;
    @ExcelProperty(value = "支付金额",index = 4)
    private BigDecimal payPrice;
    @ExcelProperty(value = "订单ID",index = 5)
    private String shopOrderId;
    @ExcelProperty(value = "商品图片",index = 6)
    private String image;
    @ExcelProperty(value = "itemOrdreID",index = 7)
    private String itemOrderId;
    @ExcelProperty(value = "商品名",index = 8)
    private String productName;
    @ExcelProperty(value = "商品售价",index = 9)
    private BigDecimal productPrice;
    @ExcelProperty(value = "skuId",index = 10)
    private String skuId;
    @ExcelProperty(value = "数量",index = 11)
    private int num;


}
