package com.copyright.mall.domain.vo.order;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author : zhangyuchen
 * @date : 2019/11/23 12:03
 */
@ApiModel
@Data
public class OrderDetailVO {

    @JSONField(name = "shopInfo")
    @ApiModelProperty("店铺信息")
    private ShopInfoBean shopInfo;
    @JSONField(name = "status")
    @ApiModelProperty("订单状态")
    private String status;
    @ApiModelProperty("订单状态描述")
    private String statusDesc;
    @JSONField(name = "payPrice")
    @ApiModelProperty("支付价格")
    private BigDecimal payPrice;
    @JSONField(name = "expressInfo")
    @ApiModelProperty("快递信息")
    private ExpressInfoBean expressInfo;
    @JSONField(name = "receiveUser")
    @ApiModelProperty("收货人信息")
    private ReceiveUserBean receiveUser;
    @JSONField(name = "orderNo")
    @ApiModelProperty("订单编号")
    private String orderNo;
    @JSONField(name = "orderCreateTime")
    @ApiModelProperty("生单时间")
    private Date orderCreateTime;
    @JSONField(name = "orderPayTime")
    @ApiModelProperty("支付时间")
    private String orderPayTime;
    @JSONField(name = "orderDeliveryTime")
    @ApiModelProperty("订单交货时间")
    private String orderDeliveryTime;
    @JSONField(name = "orderRefundTime")
    @ApiModelProperty("订单退款时间")
    private String orderRefundTime;

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
    }
    @Data
    public static class RelateProductsBean {
        @JSONField(name = "image")
        @ApiModelProperty("商品图片")
        private String image;
        @JSONField(name = "productName")
        @ApiModelProperty("商品名")
        private String productName;
        @JSONField(name = "productPrice")
        @ApiModelProperty("商品售价")
        private String productPrice;
        @JSONField(name = "productId")
        @ApiModelProperty("skuId")
        private String skuId;
        @JSONField(name = "num")
        @ApiModelProperty("数量")
        private int num;
    }
    @Data
    public static class ExpressInfoBean {

        @JSONField(name = "company")
        @ApiModelProperty("快递公司")
        private String company;
        @JSONField(name = "deliveryID")
        @ApiModelProperty("运单编号")
        private String deliveryID;

    }
    @Data
    public static class ReceiveUserBean {
        @JSONField(name = "consigneeName")
        @ApiModelProperty("收货人姓名")
        @NotBlank
        private String consigneeName;
        @JSONField(name = "consigneePhone")
        @ApiModelProperty("收货人电话")
        @NotBlank
        private String consigneePnone;
        @JSONField(name = "address")
        @ApiModelProperty("收货地址 待确定 标准省市区？")
        @NotBlank
        private String address;
    }
}
