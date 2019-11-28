package com.copyright.mall.service;

import com.copyright.mall.domain.dto.cart.CreateOrderDTO;
import com.copyright.mall.domain.dto.order.PayDTO;

/**
 * @author : zhangyuchen
 * @date : 2019/11/27 16:16
 */
public interface OrderService {

    void payOrder(PayDTO payDTO);

    String createOrder(CreateOrderDTO createOrderDTO);
}
