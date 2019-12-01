package com.copyright.mall.domain.dto.order;

import com.copyright.mall.domain.dto.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : zhangyuchen
 * @date : 2019/11/23 11:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class QueryOrderListParam extends BasePage {

    @ApiModelProperty("订单状态 --1查全部    " +
            "UNPAID(10,\"未支付\"),\n" +
            "    PAID(20,\"已支付\"),\n" +
            "    TO_BE_SHIPPED(30,\"待发货\"),\n" +
            "    SHIPPED(40,\"已发货\"),\n" +
            "    ALREADY_SIGNED(50,\"已签收\");\n")
    private Integer orderStatus;
}
