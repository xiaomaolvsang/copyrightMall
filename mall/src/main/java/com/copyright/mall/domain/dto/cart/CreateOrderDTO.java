package com.copyright.mall.domain.dto.cart;

import com.copyright.mall.domain.dto.order.CreateOrderParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : zhangyuchen
 * @date : 2019/11/28 11:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CreateOrderDTO extends CreateOrderParam {

    private Long mallId;
    private Long userId;
}
