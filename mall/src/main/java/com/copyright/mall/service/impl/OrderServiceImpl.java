package com.copyright.mall.service.impl;

import com.copyright.mall.bean.ItemOrder;
import com.copyright.mall.bean.MallOrder;
import com.copyright.mall.bean.Shop;
import com.copyright.mall.bean.ShopOrder;
import com.copyright.mall.domain.dto.order.PayDTO;
import com.copyright.mall.domain.exception.BusinessException;
import com.copyright.mall.enums.ItemOrderType;
import com.copyright.mall.enums.MallPayStatusEnum;
import com.copyright.mall.enums.ShopOrderType;
import com.copyright.mall.service.OrderService;
import com.copyright.mall.util.wrapper.WrapMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author : zhangyuchen
 * @date : 2019/11/27 16:17
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    MallOrderService mallOrderService;

    @Resource
    ShopOrderService shopOrderService;

    @Resource
    ItemOrderService itemOrderService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void payOrder(PayDTO payDTO) {
        MallOrder mallOrder = mallOrderService.selectByMallOrderID(payDTO.getMallOrderID());
        if(mallOrder==null){
            throw new BusinessException("订单不存在");
        }
        ShopOrder shopQuery = new ShopOrder();
        shopQuery.setMallOrderId(mallOrder.getMallOrderId());
        List<ShopOrder> shopOrders = shopOrderService.selectByObjectList(shopQuery);
        for(ShopOrder shopOrderItem : shopOrders){
            ItemOrder itemQuery = new ItemOrder();
            itemQuery.setShopOrderId(shopOrderItem.getShopOrderId());
            List<ItemOrder> itemOrders = itemOrderService.selectByObjectList(itemQuery);
            for(ItemOrder itemOrder : itemOrders){
                itemOrder.setPayPrice(itemOrder.getItemTotalPrice());
                itemOrder.setPayTime(new Date());
                itemOrder.setItemOrderStatus(ItemOrderType.PAID.getCode());
                itemOrderService.updateByPrimaryKeySelective(itemOrder);
            }
            shopOrderItem.setPayPrice(shopOrderItem.getPrice());
            shopOrderItem.setPayTime(new Date());
            shopOrderItem.setOrderType(ShopOrderType.PAID.getCode());
            shopOrderService.updateByPrimaryKeySelective(shopOrderItem);
        }
        mallOrder.setPayPrice(mallOrder.getPrice());
        mallOrder.setPayStatus(MallPayStatusEnum.PAID.getCode());
        mallOrder.setPayTime(new Date());
        mallOrderService.updateByPrimaryKeySelective(mallOrder);
    }
}
