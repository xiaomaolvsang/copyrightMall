package com.copyright.mall.domain.vo.cart;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : zhangyuchen
 * @date : 2019/11/20 16:34
 */
@Data
@ApiModel("购物车列表")
public class CartListVO {

    @ApiModelProperty("店铺列表")
    @JSONField(name = "shopList")
    private List<ShopListBean> shopList;

    @ApiModelProperty("当前页")
    private Integer pageNum;

    @ApiModelProperty("总条数")
    private Long total;

    @Data
    @ApiModel("店铺列表")
    public static class ShopListBean {
        @ApiModelProperty("店铺名称")
        @JSONField(name = "shopName")
        private String shopName;

        @ApiModelProperty("店铺ID")
        @JSONField(name = "shopId")
        private Long shopId;

        @ApiModelProperty("商品列表")
        @JSONField(name = "relateProducts")
        private List<RelateProductsBean> relateProducts;

        @ApiModelProperty("总金额")
        private String totalPrice;
    }
    @Data
    @ApiModel("商品列表")
    public static class RelateProductsBean {
        @JSONField(name = "image")
        private String image;
        @JSONField(name = "institutionName")
        private String institutionName;
        @JSONField(name = "productName")
        private String productName;
        @JSONField(name = "productPrice")
        private BigDecimal productPrice;
        @JSONField(name = "productId")
        private Long productId;
        @JSONField(name = "num")
        private int num;
    }

}
