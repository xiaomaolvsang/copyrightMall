package com.copyright.mall.domain.dto.order;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author : zhangyuchen
 * @date : 2019/11/23 11:37
 */
@Data
@ApiModel
public class CreateOrderParam {

    /**
     * 商品集合
     */
    @ApiModelProperty(value = "店铺订单集合",required = true)
    @NotEmpty(message = "店铺集合不能为空")
    private List<ShopInfo> shopInfoBeans;

    private ReceiveUserBean receiveUserBean;


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
    public static class ShopInfo{
        /**
         * 店铺ID
         */
        @ApiModelProperty(value = "店铺ID",required = true)
        @NotNull
        private Long shopId;

        /**
         * sku列表
         */
        @ApiModelProperty(value = "skuIds",required = true)
        @NotEmpty
        private List<SKU> skus;

        @Data
        public static class SKU{
            @ApiModelProperty(value = "SKUID",required = true)
            @NotNull
            private Long skuId;
            @ApiModelProperty(value = "数量",required = true)
            @NotNull
            private Integer num;
        }

    }
}
