package com.copyright.mall.domain.vo.order;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : zhangyuchen
 * @date : 2019/11/23 11:54
 */
@Data
@ApiModel
public class OrderInfoVO {

    @JSONField(name = "shopInfo")
    @ApiModelProperty("店铺信息")
    private List<ShopInfoBean> shopInfo;
    @Data
    public static class ShopInfoBean {

        @JSONField(name = "shopId")
        @ApiModelProperty("店铺ID")
        private String shopId;
        @JSONField(name = "shopName")
        @ApiModelProperty("店铺名称")
        private String shopName;
        @ApiModelProperty("商品信息列表")
        private List<RelateProductsBean> relateProducts;
        @JSONField(name = "status")
        @ApiModelProperty("订单状态")
        private String orderStatus;
        @ApiModelProperty("订单状态枚举")
        private String statusDesc;
        @JSONField(name = "payPrice")
        @ApiModelProperty("支付金额")
        private BigDecimal payPrice;
        @JSONField(name = "shopOrderId")
        @ApiModelProperty("订单ID")
        private String shopOrderId;
    }
    @Data
    public static class RelateProductsBean {
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
        private BigDecimal productPrice;
        @JSONField(name = "productId")
        @ApiModelProperty("skuId")
        private String skuId;
        @JSONField(name = "num")
        @ApiModelProperty("数量")
        private int num;
        @ApiModelProperty("虚拟商品")
        private boolean virtual;
    }

}
