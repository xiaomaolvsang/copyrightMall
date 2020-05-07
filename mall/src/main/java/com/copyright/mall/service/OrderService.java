package com.copyright.mall.service;

import com.copyright.mall.bean.ShopOrder;
import com.copyright.mall.domain.dto.cart.CreateOrderDTO;
import com.copyright.mall.domain.dto.order.PayDTO;
import com.copyright.mall.manage.domain.dto.ModifyPriceParam;
import com.copyright.mall.manage.domain.dto.QueryOrderListParam;
import com.copyright.mall.manage.domain.dto.ShopOrderDetail;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author : zhangyuchen
 * @date : 2019/11/27 16:16
 */
public interface OrderService {

    void payOrder(PayDTO payDTO);

    String createOrder(CreateOrderDTO createOrderDTO);

    List<ShopOrderDetail> orderDetailList(QueryOrderListParam queryOrderListParam);

    void cancelOrder(String orderNo);

    void modifyOrder(ModifyPriceParam modifyPriceParam);
}
