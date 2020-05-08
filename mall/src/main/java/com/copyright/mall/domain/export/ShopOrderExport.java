package com.copyright.mall.domain.export;


import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ShopOrderExport {

    @ExcelProperty(value = "店铺ID",index = 0)
    private String shopId;
    @ExcelProperty(value = "店铺名称",index = 1)
    private String shopName;
    @ExcelProperty(value = "订单状态枚举",index = 2)
    private String statusDesc;
    @ExcelProperty(value = "支付金额",index = 4)
    private BigDecimal payPrice;
    @ExcelProperty(value = "支付时间",index = 5)
    private Date payTime;
    @ExcelProperty(value = "订单ID",index = 7)
    private String shopOrderId;
    @ExcelProperty(value = "商品图片",index =8)
    private String image;
    @ExcelProperty(value = "商品名",index = 9)
    private String productName;
    @ExcelProperty(value = "商品售价",index = 10)
    private BigDecimal productPrice;
    @ExcelProperty(value = "skuId",index = 11)
    private String skuId;
    @ExcelProperty(value = "数量",index = 12)
    private int num;
    @ExcelProperty(value = "快递公司",index = 13)
    private String delliveryCompanyName;
    @ExcelProperty(value = "运单号",index = 14)
    private String deliveryOrderId;
    @ExcelProperty(value = "创建时间",index = 15)
    private String createTime;


}
