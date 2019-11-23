package com.copyright.mall.domain.vo.order;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author : zhangyuchen
 * @date : 2019/11/23 11:22
 */
@ApiModel
@Data
public class ConfirmOrderVO {

    @JSONField(name = "receiveUser")
    @ApiModelProperty("接收用户信息 这个信息是这个接口该返回的么？")
    private ReceiveUserBean receiveUser;
    @JSONField(name = "orderDesc")
    @ApiModelProperty("订单描述")
    private String orderDesc;
    @JSONField(name = "totalPayPrice")
    @ApiModelProperty("总价格")
    private BigDecimal totalPayPrice;
    @JSONField(name = "products")
    @ApiModelProperty("商品信息列表")
    private List<ProductsBean> products;

    @Data
    public static class ReceiveUserBean {
        @JSONField(name = "avatar")
        @ApiModelProperty("用户头像")
        private String avatar;
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
    @Data
    public static class ProductsBean {
        /**
         * image :
         * institutionName :
         * productName :
         * productPrice :
         * productId :
         * num : 0
         */

        @JSONField(name = "image")
        @ApiModelProperty("商品图片")
        private String image;
        @JSONField(name = "shopName")
        @ApiModelProperty("shopnName")
        private String shopName;
        @JSONField(name = "productName")
        @ApiModelProperty("商品名")
        private String productName;
        @JSONField(name = "productPrice")
        @ApiModelProperty("商品售价")
        private String productPrice;
        @JSONField(name = "productId")
        @ApiModelProperty("商品ID")
        private String productId;
        @JSONField(name = "num")
        @ApiModelProperty("数量")
        private int num;
    }
}
