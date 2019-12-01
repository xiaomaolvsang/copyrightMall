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

    @ApiModelProperty("订单状态 " +
            "UNPAID(10,\"未支付\"),\n" +
            "PAID(20,\"已支付\");")
    private Integer orderStatus;
}
