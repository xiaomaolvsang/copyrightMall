package com.copyright.mall.domain.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author : zhangyuchen
 * @date : 2019/11/23 11:26
 */
@Data
@ApiModel
public class ConfirmOrderParam {
    /**
     * 商品集合
     */
    @ApiModelProperty(value = "商品集合",required = true)
    @NotEmpty(message = "商品集合不能为空")
    private List<SKU> skus;

    private String orderDesc;

    /**
     * 地址ID
     */
    @ApiModelProperty(value = "地址ID")
    private Long  receiveId;

    @Data
    public static class SKU{
        @ApiModelProperty(value = "SKUID",required = true)
        @NotNull(message = "skuId不能为空")
        private Long skuId;
        @ApiModelProperty(value = "数量",required = true)
        @NotNull(message = "数量不能为空")
        private Integer num;
    }

}
