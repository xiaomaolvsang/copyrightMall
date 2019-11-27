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
import com.copyright.mall.util.BeanMapperUtils;
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
        ConfirmOrderVO result = new ConfirmOrderVO();
        ConfirmOrderVO.ReceiveUserBean receiveUserBean = BeanMapperUtils.map(confirmOrderParam.getReceiveUserBean(), ConfirmOrderVO.ReceiveUserBean.class);
        result.setReceiveUser(receiveUserBean);
        result.setOrderDesc(confirmOrderParam.getOrderDesc());
        List<ConfirmOrderVO.ProductsBean> productsBeans = Lists.newArrayList();
        int totalPrice = 0;
        for(ConfirmOrderParam.SKU dtoItem: confirmOrderParam.getSkus()){
            ConfirmOrderVO.ProductsBean productsBean = new ConfirmOrderVO.ProductsBean();
            Sku sku = skuService.selectByPrimaryKey(dtoItem.getSkuId());
            if(sku==null){
                log.warn("商品不存在{}",dtoItem.getSkuId());
                return WrapMapper.error("商品不存在");
            }
            Item item = itemService.selectByPrimaryKey(sku.getItemId());
            if(item==null){
                log.warn("商品不存在{}",sku.getItemId());
                return WrapMapper.error("商品不存在");
            }
            Shop shop = shopService.selectByPrimaryKey(item.getShopId());
            productsBean.setImage(item.getTitleImg());
            productsBean.setShopName(shop.getShopName());
            productsBean.setProductName(item.getItemTitle());
            totalPrice+=sku.getPrice()*dtoItem.getNum();
            productsBean.setProductPrice(PriceFormat.formatStr(sku.getPrice()*dtoItem.getNum()));
            productsBean.setProductId(sku.getId());
            productsBean.setNum(dtoItem.getNum());
            productsBeans.add(productsBean);
        }
        result.setProducts(productsBeans);
        result.setTotalPayPrice(PriceFormat.format(totalPrice));
        return WrapMapper.ok(result);

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
        MallOrder mallOrder = mallOrderService.selectByPrimaryKey(Long.valueOf(shopOrder.getMallOrderId()));
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
        OrderDetailVO.ReceiveUserBean receiveUserBean = new OrderDetailVO.ReceiveUserBean();
        receiveUserBean.setAddress(mallOrder.getDeliveryAddress());
        receiveUserBean.setConsigneeName(mallOrder.getDeliveryName());
        receiveUserBean.setConsigneePnone(mallOrder.getPhone());
        orderDetailVO.setReceiveUser(receiveUserBean);
        orderDetailVO.setOrderNo(shopOrder.getId().toString());
        orderDetailVO.setOrderCreateTime(shopOrder.getOrderCreateTime());
        //todo 没有支付时间
        //orderDetailVO.setOrderPayTime();
        //todo 交货时间
        //orderDetailVO.setOrderDeliveryTime(mallOrder);
        //todo 退货时间
        //orderDetailVO.setOrderRefundTime();

        return WrapMapper.ok();
    }

}
