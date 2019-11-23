package com.copyright.mall.domain.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
     * 店铺名
     */
    @ApiModelProperty(value = "店铺ID",required = true)
    @NotNull
    private Integer shopId;

    /**
     * 商品集合
     */
    @ApiModelProperty(value = "商品集合",required = true)
    @NotEmpty
    private List<ConfirmOrderParam.SKU> skus;


    @Data
    class SKU{
        @ApiModelProperty(value = "SKUID",required = true)
        @NotNull
        private Integer skuId;
        @ApiModelProperty(value = "数量",required = true)
        @NotNull
        private Integer num;
    }


}
