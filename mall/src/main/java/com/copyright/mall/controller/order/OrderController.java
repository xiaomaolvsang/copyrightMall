package com.copyright.mall.controller.order;

import com.copyright.mall.bean.*;
import com.copyright.mall.controller.BaseController;
import com.copyright.mall.domain.dto.order.ConfirmOrderParam;
import com.copyright.mall.domain.dto.order.CreateOrderParam;
import com.copyright.mall.domain.dto.order.QueryOrderListParam;
import com.copyright.mall.domain.vo.order.ConfirmOrderVO;
import com.copyright.mall.domain.vo.order.CreateOrderVO;
import com.copyright.mall.domain.vo.order.OrderDetailVO;
import com.copyright.mall.domain.vo.order.OrderInfoVO;
import com.copyright.mall.service.*;
import com.copyright.mall.service.impl.ItemService;
import com.copyright.mall.util.PriceFormat;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : zhangyuchen
 * @date : 2019/11/23 11:21
 */
@Api(tags = "订单接口")
@Slf4j
@RestController
@RequestMapping("/v1/order")
public class OrderController extends BaseController {

    @Resource
    private IMallOrderService mallOrderService;

    @Resource
    private IShopOrderService shopOrderService;

    @Resource
    private IShopService shopService;

    @Resource
    private IItemService itemService;

    @Resource
    private ISkuService skuService;

    @Resource
    private IItemOrderService iItemOrderService;


    @PostMapping("/confirmOrder")
    @ApiOperation("确认订单")
    public Wrapper<ConfirmOrderVO> confirmOrder(@ApiParam @Valid @RequestBody ConfirmOrderParam confirmOrderParam){

        return WrapMapper.ok();

    }

    @PostMapping("/createOrder")
    @ApiOperation("生单")
    public Wrapper<CreateOrderVO> createOrder(@ApiParam @Valid @RequestBody CreateOrderParam createOrderParam){
        return  WrapMapper.ok();
    }

    @GetMapping("/orderList")
    @ApiOperation("订单列表")
    public Wrapper<List<OrderInfoVO>> orderList(@ApiParam @Valid @RequestBody QueryOrderListParam queryOrderListParam){
        ShopOrder queryParam = new ShopOrder();
        queryParam.setMallOrderId(this.getMallId().toString());
        queryParam.setOrderType(queryOrderListParam.getOrderStatus());
        List<ShopOrder> shopOrders = shopOrderService.selectByObjectList(queryParam);
        Map<Long,List<ShopOrder>> integerListMap =shopOrders.stream().collect(Collectors.groupingBy(ShopOrder::getShopId,Collectors.toList()));
        List<OrderInfoVO> orderInfoVOS = Lists.newArrayList();
        for(Map.Entry<Long,List<ShopOrder>> entry : integerListMap.entrySet()){
            Shop shop = shopService.selectByPrimaryKey(entry.getKey());
            OrderInfoVO orderInfoVO = new OrderInfoVO();
            OrderInfoVO.ShopInfoBean shopInfoBean = new OrderInfoVO.ShopInfoBean();
            shopInfoBean.setShopId(entry.getKey().toString());
            shopInfoBean.setShopName(shop.getShopName());
            for(ShopOrder shopOrder : entry.getValue()) {
                List<OrderInfoVO.RelateProductsBean> relateProductsBeanArrayList = Lists.newArrayList();
                ItemOrder itemQueryParam = new ItemOrder();
                itemQueryParam.setShopOrderId(shopOrder.getShopOrderId());
                itemQueryParam.setItemOrderStatus(queryOrderListParam.getOrderStatus());
                List<ItemOrder> itemOrders = iItemOrderService.selectByObjectList(itemQueryParam);
                for(ItemOrder itemOrder : itemOrders) {
                    OrderInfoVO.RelateProductsBean relateProductsBean = new OrderInfoVO.RelateProductsBean();
                    Sku sku = skuService.selectByPrimaryKey(itemOrder.getSkuId());
                    Item item = itemService.selectByPrimaryKey(itemOrder.getItemId());
                    relateProductsBean.setImage(item.getTitleImg());
                    relateProductsBean.setProductName(item.getItemTitle());
                    relateProductsBean.setProductPrice(PriceFormat.formatStr(item.getPrice()));
                    relateProductsBean.setSkuId(sku.getId().toString());
                    relateProductsBean.setNum(itemOrder.getItemCount());
                    relateProductsBeanArrayList.add(relateProductsBean);
                }
                shopInfoBean.setRelateProducts(relateProductsBeanArrayList);
                shopInfoBean.setShopId(shop.getId().toString());
                shopInfoBean.setShopName(shop.getShopName());
                shopInfoBean.setOrderStatus(shopOrder.getOrderType().toString());
                shopInfoBean.setStatusDesc(shopOrder.getOrderType().toString());
                shopInfoBean.setPayPrice(PriceFormat.format(shopOrder.getPrice()));
                shopInfoBean.setOrderId(shop.getId().toString());

            }
            orderInfoVO.setShopInfo(shopInfoBean);
            orderInfoVOS.add(orderInfoVO);
        }

        return WrapMapper.ok(orderInfoVOS);
    }

    @GetMapping("/orderDetail/{orderNo}")
    @ApiOperation("/订单详情")
    public Wrapper<OrderDetailVO> getOrderDetail(@PathVariable("orderNo") Long orderNo){
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        ShopOrder shopOrder = shopOrderService.selectByPrimaryKey(orderNo);
        Shop shop = shopService.selectByPrimaryKey(shopOrder.getShopId());
        OrderDetailVO.ShopInfoBean shopInfoBean = new OrderDetailVO.ShopInfoBean();
        shopInfoBean.setShopId(shop.getId().toString());
        shopInfoBean.setShopName(shop.getShopName());
        ItemOrder itemQueryParam = new ItemOrder();
        itemQueryParam.setShopOrderId(shopOrder.getShopOrderId());
        List<ItemOrder> itemOrders = iItemOrderService.selectByObjectList(itemQueryParam);
        List<OrderDetailVO.RelateProductsBean> relateProductsBeans = Lists.newArrayList();
        for(ItemOrder itemOrder : itemOrders){
            OrderDetailVO.RelateProductsBean relateProductsBean = new OrderDetailVO.RelateProductsBean();
            Sku sku = skuService.selectByPrimaryKey(itemOrder.getSkuId());
            Item item = itemService.selectByPrimaryKey(itemOrder.getItemId());
            relateProductsBean.setImage(item.getTitleImg());
            relateProductsBean.setProductName(item.getItemTitle());
            relateProductsBean.setProductPrice(PriceFormat.formatStr(item.getPrice()));
            relateProductsBean.setSkuId(sku.getId().toString());
            relateProductsBean.setNum(itemOrder.getItemCount());
            relateProductsBeans.add(relateProductsBean);
        }
        shopInfoBean.setRelateProducts(relateProductsBeans);
        orderDetailVO.setShopInfo(shopInfoBean);
        orderDetailVO.setStatus(shopOrder.getOrderType().toString());
        orderDetailVO.setStatusDesc(shopOrder.getOrderType().toString());
        orderDetailVO.setPayPrice(PriceFormat.format(shopOrder.getPrice()));
        OrderDetailVO.ExpressInfoBean expressInfoBean = new OrderDetailVO.ExpressInfoBean();
        expressInfoBean.setCompany(shopOrder.getDelliveryCompanyName());
        expressInfoBean.setDeliveryID(shopOrder.getDeliveryOrderId());
        orderDetailVO.setExpressInfo(expressInfoBean);
        //todo 收货人在结算单里
        orderDetailVO.setReceiveUser();
        orderDetailVO.setOrderNo(shopOrder.getId().toString());
        orderDetailVO.setOrderCreateTime(shopOrder.getOrderCreateTime());
        //todo 没有支付时间
        orderDetailVO.setOrderPayTime();
        //todo 交货时间
        orderDetailVO.setOrderDeliveryTime();
        //todo 退货时间
        orderDetailVO.setOrderRefundTime();

        return WrapMapper.ok();
    }

}
