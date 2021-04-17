package com.copyright.mall.service.impl;

import com.copyright.mall.bean.Item;
import com.copyright.mall.bean.ItemOrder;
import com.copyright.mall.bean.MallOrder;
import com.copyright.mall.bean.ShopOrder;
import com.copyright.mall.bean.Sku;
import com.copyright.mall.bean.UserAddress;
import com.copyright.mall.dao.ShopOrderMapper;
import com.copyright.mall.domain.dto.cart.CreateOrderDTO;
import com.copyright.mall.domain.dto.cart.DeleteCartParam;
import com.copyright.mall.domain.dto.order.CreateOrderParam;
import com.copyright.mall.domain.dto.order.PayDTO;
import com.copyright.mall.domain.exception.BusinessException;
import com.copyright.mall.enums.ItemOrderType;
import com.copyright.mall.enums.MallPayStatusEnum;
import com.copyright.mall.enums.ShopOrderType;
import com.copyright.mall.manage.domain.dto.ModifyPriceParam;
import com.copyright.mall.manage.domain.dto.QueryOrderListParam;
import com.copyright.mall.manage.domain.dto.ShopItemOrderDetail;
import com.copyright.mall.service.ICartService;
import com.copyright.mall.service.IItemOrderService;
import com.copyright.mall.service.IItemService;
import com.copyright.mall.service.IMallOrderService;
import com.copyright.mall.service.IShopOrderService;
import com.copyright.mall.service.ISkuService;
import com.copyright.mall.service.IUserAddressService;
import com.copyright.mall.service.OrderService;
import com.copyright.mall.util.IDUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private IMallOrderService mallOrderService;

    @Resource
    private IShopOrderService shopOrderService;

    @Resource
    private IItemService itemService;

    @Resource
    private ISkuService skuService;

    @Resource
    private IItemOrderService iItemOrderService;

    @Resource
    private ICartService cartService;

    @Resource
    private IUserAddressService userAddressService;

    @Resource
    private ShopOrderMapper shopOrderMapper;

    @Resource
    private ItemOrderService itemOrderService;

    
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void payOrder(PayDTO payDTO) {
        if(payDTO.getOrderId().startsWith("MID")) {
            MallOrder mallOrder = mallOrderService.selectByMallOrderID(payDTO.getOrderId());
            if (mallOrder == null) {
                throw new BusinessException("订单不存在");
            }
            ShopOrder shopQuery = new ShopOrder();
            shopQuery.setMallOrderId(mallOrder.getMallOrderId());
            List<ShopOrder> shopOrders = shopOrderService.selectByObjectList(shopQuery);
            for (ShopOrder shopOrderItem : shopOrders) {
                ItemOrder itemQuery = new ItemOrder();
                itemQuery.setShopOrderId(shopOrderItem.getShopOrderId());
                List<ItemOrder> itemOrders = iItemOrderService.selectByObjectList(itemQuery);
                for (ItemOrder itemOrder : itemOrders) {
                    itemOrder.setPayPrice(itemOrder.getItemTotalPrice());
                    itemOrder.setPayTime(new Date());
                    itemOrder.setItemOrderStatus(ShopOrderType.TO_BE_SHIPPED.getCode());
                    iItemOrderService.updateByPrimaryKeySelective(itemOrder);
                }
                shopOrderItem.setPayPrice(shopOrderItem.getPrice());
                shopOrderItem.setPayTime(new Date());
                shopOrderItem.setOrderType(ShopOrderType.TO_BE_SHIPPED.getCode());
                shopOrderService.updateByPrimaryKeySelective(shopOrderItem);
            }
            mallOrder.setPayPrice(mallOrder.getPrice());
            mallOrder.setPayStatus(ShopOrderType.TO_BE_SHIPPED.getCode());
            mallOrder.setPayTime(new Date());
            mallOrderService.updateByPrimaryKeySelective(mallOrder);
        }
        if(payDTO.getOrderId().startsWith("SID")){
            ShopOrder shopQuery = new ShopOrder();
            shopQuery.setShopOrderId(payDTO.getOrderId());
            List<ShopOrder> shopOrders = shopOrderService.selectByObjectList(shopQuery);
            for (ShopOrder shopOrderItem : shopOrders) {
                ItemOrder itemQuery = new ItemOrder();
                itemQuery.setShopOrderId(shopOrderItem.getShopOrderId());
                List<ItemOrder> itemOrders = iItemOrderService.selectByObjectList(itemQuery);
                for (ItemOrder itemOrder : itemOrders) {
                    itemOrder.setPayPrice(itemOrder.getItemTotalPrice());
                    itemOrder.setPayTime(new Date());
                    itemOrder.setItemOrderStatus(ShopOrderType.TO_BE_SHIPPED.getCode());
                    iItemOrderService.updateByPrimaryKeySelective(itemOrder);
                }
                shopOrderItem.setPayPrice(shopOrderItem.getPrice());
                shopOrderItem.setPayTime(new Date());
                shopOrderItem.setOrderType(ShopOrderType.TO_BE_SHIPPED.getCode());
                shopOrderService.updateByPrimaryKeySelective(shopOrderItem);
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public MallOrder createOrder(CreateOrderDTO createOrderDTO) {
        Integer mallTotalPrice =0;
        MallOrder mallOrder = new MallOrder();
        mallOrder.setMallOrderId(IDUtil.generatorID("MID"));
        mallOrder.setMallId(createOrderDTO.getMallId().toString());
        mallOrder.setPayStatus(MallPayStatusEnum.UNPAID.getCode());
        mallOrder.setBuyer(createOrderDTO.getUserId().toString());
        UserAddress userAddress = userAddressService.selectByPrimaryKey(createOrderDTO.getReceiveId());
        mallOrder.setDeliveryAddress(userAddress.getDetail());
        mallOrder.setDeliveryName(userAddress.getConsigneeName());
        mallOrder.setPhone(userAddress.getConsigneePhone());
        List<Long> deleteSkus = Lists.newArrayList();
        for(CreateOrderParam.ShopInfo shopInfo : createOrderDTO.getShopInfoBeans()) {
            Integer shopTotalPrice = 0;
            ShopOrder shopOrder = new ShopOrder();
            shopOrder.setMallOrderId(mallOrder.getMallOrderId());
            shopOrder.setShopOrderId(IDUtil.generatorID("SID"));
            shopOrder.setShopId(shopInfo.getShopId());
            for (CreateOrderParam.ShopInfo.SKU skuItem : shopInfo.getSkus()) {
                Sku sku = skuService.selectByPrimaryKeyFromDBWithIncrSoldInventory(skuItem.getSkuId(),skuItem.getNum());
                if (sku == null) {
                    log.warn("商品不存在{}", skuItem.getSkuId());
                    throw new BusinessException("商品不存在");
                }
                deleteSkus.add(sku.getId());
                if(sku.getInventory() < sku.getSoldInventory()){
                    log.info("inventor insufficient skip");
                    continue;
                }
                Item item = itemService.selectByPrimaryKey(sku.getItemId());
                if (item == null) {
                    log.warn("商品不存在{}", sku.getItemId());
                    throw new BusinessException("商品不存在");
                }
                if(!item.getShopId().equals(shopInfo.getShopId())){
                    throw new BusinessException("商品和所选店铺不符");
                }
                ItemOrder itemOrder = new ItemOrder();
                itemOrder.setItemOrderId(IDUtil.generatorID("TID"));
                itemOrder.setShopOrderId(shopOrder.getShopOrderId());
                itemOrder.setItemId(item.getId());
                itemOrder.setSkuId(skuItem.getSkuId());
                itemOrder.setItemOrderStatus(ItemOrderType.UNPAID.getCode());
                itemOrder.setItemPrice(sku.getPrice());
                itemOrder.setItemCount(skuItem.getNum());
                itemOrder.setItemTotalPrice(sku.getPrice() * skuItem.getNum());
                iItemOrderService.insertSelective(itemOrder);
                skuService.incrSoldInventoryByPrimaryKey(sku.getId(), sku.getSoldInventory() - skuItem.getNum(), skuItem.getNum());
                shopTotalPrice += itemOrder.getItemTotalPrice();
            }
            shopOrder.setOrderType(ShopOrderType.UNPAID.getCode());
            shopOrder.setPrice(shopTotalPrice);
            shopOrderService.insertSelective(shopOrder);
            mallTotalPrice+=shopOrder.getPrice();
        }
        if(mallTotalPrice == 0 ){
            throw new BusinessException("全部商品暂无库存");
        }
        mallOrder.setPrice(mallTotalPrice);
        mallOrderService.insertSelective(mallOrder);
        DeleteCartParam deleteCartParam = new DeleteCartParam();
        deleteCartParam.setUserId(createOrderDTO.getUserId());
        deleteCartParam.setSkus(deleteSkus);
        cartService.deleteBySkus(deleteCartParam);
        return mallOrder;
    }

    @Override
    public List <ShopItemOrderDetail> orderDetailList(QueryOrderListParam queryOrderListParam) {
        return shopOrderMapper.selectOrderDetailList(queryOrderListParam);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelOrder(String orderNo) {
        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setShopOrderId(orderNo);
        shopOrder.setOrderType(ShopOrderType.CANCEL.getCode());
        shopOrderService.updateByShopOrderId(shopOrder);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyOrder(ModifyPriceParam modifyPriceParam) {
        ItemOrder itemOrder = itemOrderService.selectShoporderAndItemId(
                modifyPriceParam.getOrderId(),Long.valueOf(modifyPriceParam.getItemId()));
        Integer newTotal = new Double(modifyPriceParam.getItemPrice()*100d).intValue()*itemOrder.getItemCount();
        Integer cha = newTotal - itemOrder.getItemTotalPrice() ;
        itemOrder.setShopOrderId(modifyPriceParam.getOrderId());
        itemOrder.setItemId(Long.valueOf(modifyPriceParam.getItemId()));
        itemOrder.setItemPrice(new Double(modifyPriceParam.getItemPrice()* 100d).intValue());
        itemOrder.setItemTotalPrice(newTotal);
        itemOrderService.updateByShopOrderIdAndItemIdSelective(itemOrder);

        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setShopOrderId(modifyPriceParam.getOrderId());
        shopOrder = shopOrderService.selectByShopOrderId(modifyPriceParam.getOrderId());
        shopOrder.setPrice(shopOrder.getPrice() + cha);
        shopOrderService.updateByShopOrderId(shopOrder);
    }
}
