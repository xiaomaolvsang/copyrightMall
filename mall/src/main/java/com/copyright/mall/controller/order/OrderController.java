package com.copyright.mall.controller.order;

import com.copyright.mall.bean.*;
import com.copyright.mall.controller.BaseController;
import com.copyright.mall.domain.dto.cart.CreateOrderDTO;
import com.copyright.mall.domain.dto.cart.DeleteCartParam;
import com.copyright.mall.domain.dto.order.ConfirmOrderParam;
import com.copyright.mall.domain.dto.order.CreateOrderParam;
import com.copyright.mall.domain.dto.order.PayDTO;
import com.copyright.mall.domain.dto.order.QueryOrderListParam;
import com.copyright.mall.domain.vo.order.ConfirmOrderVO;
import com.copyright.mall.domain.vo.order.CreateOrderVO;
import com.copyright.mall.domain.vo.order.OrderDetailVO;
import com.copyright.mall.domain.vo.order.OrderInfoVO;
import com.copyright.mall.enums.ItemOrderType;
import com.copyright.mall.enums.MallPayStatusEnum;
import com.copyright.mall.enums.ShopOrderType;
import com.copyright.mall.service.*;
import com.copyright.mall.util.BeanMapperUtils;
import com.copyright.mall.util.IDUtil;
import com.copyright.mall.util.PriceFormat;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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

    @Resource
    private OrderService orderService;

    @Resource
    private IUserAddressService userAddressService;


    @PostMapping("/confirmOrder")
    @ApiOperation("确认订单")
    public Wrapper<ConfirmOrderVO> confirmOrder(@ApiParam @Valid @RequestBody ConfirmOrderParam confirmOrderParam) {
        ConfirmOrderVO result = new ConfirmOrderVO();
        UserAddress userAddress =  userAddressService.selectByPrimaryKey(confirmOrderParam.getReceiveId());
        if(userAddress!=null){
            ConfirmOrderVO.ReceiveUserBean receiveUserBean = new ConfirmOrderVO.ReceiveUserBean();
            receiveUserBean.setConsigneeName(userAddress.getConsigneeName());
            receiveUserBean.setConsigneePnone(userAddress.getConsigneePhone());
            receiveUserBean.setAddress(userAddress.getDetail());
            result.setReceiveUser(receiveUserBean);
        }
        result.setOrderDesc(confirmOrderParam.getOrderDesc());
        List<ConfirmOrderVO.ProductsBean> productsBeans = Lists.newArrayList();
        int totalPrice = 0;
        for (ConfirmOrderParam.SKU dtoItem : confirmOrderParam.getSkus()) {
            ConfirmOrderVO.ProductsBean productsBean = new ConfirmOrderVO.ProductsBean();
            Sku sku = skuService.selectByPrimaryKey(dtoItem.getSkuId());
            if (sku == null) {
                log.warn("商品不存在{}", dtoItem.getSkuId());
                return WrapMapper.error("商品不存在");
            }
            Item item = itemService.selectByPrimaryKey(sku.getItemId());
            if (item == null) {
                log.warn("商品不存在{}", sku.getItemId());
                return WrapMapper.error("商品不存在");
            }
            Shop shop = shopService.selectByPrimaryKey(item.getShopId());
            productsBean.setImage(item.getTitleImg());
            productsBean.setShopId(shop.getId());
            productsBean.setShopName(shop.getShopName());
            productsBean.setProductName(item.getItemTitle());
            totalPrice += sku.getPrice() * dtoItem.getNum();
            productsBean.setProductPrice(PriceFormat.formatStr(sku.getPrice() * dtoItem.getNum()));
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
    public Wrapper<CreateOrderVO> createOrder(@ApiParam @Valid @RequestBody CreateOrderParam createOrderParam) {
        CreateOrderDTO createOrderDTO = BeanMapperUtils.map(createOrderParam, CreateOrderDTO.class);
        createOrderDTO.setUserId(this.getUserId());
        createOrderDTO.setMallId(this.getMallId());
        String mallOrderId = orderService.createOrder(createOrderDTO);
        CreateOrderVO createOrderVO = new CreateOrderVO();
        createOrderVO.setOrderNo(mallOrderId);
        return WrapMapper.ok(createOrderVO);
    }

    @GetMapping("/orderList")
    @ApiOperation("订单列表")
    public Wrapper<PageInfo<OrderInfoVO>> orderList(@ApiParam @Valid QueryOrderListParam queryOrderListParam) {
        MallOrder mallQueryParam = new MallOrder();
        mallQueryParam.setBuyer(this.getUserId().toString());
        mallQueryParam.setMallId(this.getMallId().toString());
        if(queryOrderListParam.getOrderStatus()!=-1){
            mallQueryParam.setPayStatus(queryOrderListParam.getOrderStatus());
        }
        Page<MallOrder> page = PageHelper.startPage(queryOrderListParam.getPageNum(), queryOrderListParam.getPageSize());
        List<MallOrder> mallOrders = mallOrderService.selectByObjectList(mallQueryParam);
        List<OrderInfoVO> orderInfoVOS = Lists.newArrayList();
        for (MallOrder mallOrder : mallOrders) {
            ShopOrder queryParam = new ShopOrder();
            queryParam.setMallOrderId(mallOrder.getMallOrderId());
            queryParam.setOrderType(queryOrderListParam.getOrderStatus());
            List<ShopOrder> shopOrders = shopOrderService.selectByObjectList(queryParam);
            List<OrderInfoVO.ShopInfoBean> shopInfoBeans = Lists.newArrayList();
            OrderInfoVO orderInfoVO = new OrderInfoVO();
            for (ShopOrder shopOrder : shopOrders) {
                Shop shop = shopService.selectByPrimaryKey(shopOrder.getShopId());
                OrderInfoVO.ShopInfoBean shopInfoBean = new OrderInfoVO.ShopInfoBean();
                shopInfoBean.setShopId(shopOrder.getShopId().toString());
                shopInfoBean.setShopName(shop.getShopName());
                shopInfoBean.setShopOrderId(shopOrder.getShopOrderId());
                List<OrderInfoVO.RelateProductsBean> relateProductsBeanArrayList = Lists.newArrayList();
                ItemOrder itemQueryParam = new ItemOrder();
                itemQueryParam.setShopOrderId(shopOrder.getShopOrderId());
                itemQueryParam.setItemOrderStatus(queryOrderListParam.getOrderStatus());
                List<ItemOrder> itemOrders = iItemOrderService.selectByObjectList(itemQueryParam);
                for (ItemOrder itemOrder : itemOrders) {
                    OrderInfoVO.RelateProductsBean relateProductsBean = new OrderInfoVO.RelateProductsBean();
                    Sku sku = skuService.selectByPrimaryKey(itemOrder.getSkuId());
                    if (sku == null) {
                        log.warn("商品不存在{}", itemOrder.getSkuId());
                        return WrapMapper.error("商品不存在");
                    }
                    Item item = itemService.selectByPrimaryKey(sku.getItemId());
                    if (item == null) {
                        log.warn("商品不存在{}", sku.getItemId());
                        return WrapMapper.error("商品不存在");
                    }
                    relateProductsBean.setImage(item.getTitleImg());
                    relateProductsBean.setProductName(item.getItemTitle());
                    relateProductsBean.setProductPrice(PriceFormat.formatStr(itemOrder.getItemPrice()));
                    relateProductsBean.setSkuId(sku.getId().toString());
                    relateProductsBean.setNum(itemOrder.getItemCount());
                    relateProductsBean.setItemOrderId(itemOrder.getItemOrderId());
                    relateProductsBeanArrayList.add(relateProductsBean);
                }
                shopInfoBean.setRelateProducts(relateProductsBeanArrayList);
                shopInfoBean.setShopId(shop.getId().toString());
                shopInfoBean.setShopName(shop.getShopName());
                shopInfoBean.setOrderStatus(shopOrder.getOrderType().toString());
                shopInfoBean.setStatusDesc(ShopOrderType.valueOf(shopOrder.getOrderType()).getDesc());
                shopInfoBean.setPayPrice(PriceFormat.format(shopOrder.getPrice()));
                shopInfoBeans.add(shopInfoBean);
            }
            orderInfoVO.setShopInfo(shopInfoBeans);
            orderInfoVO.setMallOrderNO(mallOrder.getMallOrderId());
            orderInfoVOS.add(orderInfoVO);
        }
        PageInfo<OrderInfoVO> result = PageInfo.of(orderInfoVOS);
        result.setTotal(page.getTotal());
        return WrapMapper.ok(result);
    }

    @GetMapping("/orderDetail/{shopOrderId}")
    @ApiOperation("订单详情")
    public Wrapper<OrderDetailVO> getOrderDetail(@PathVariable("shopOrderId") String shopOrderId) {
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        ShopOrder shopOrder = shopOrderService.selectByShopOrderId(shopOrderId);
        Shop shop = shopService.selectByPrimaryKey(shopOrder.getShopId());
        MallOrder mallOrder = mallOrderService.selectByMallOrderID(shopOrder.getMallOrderId());
        orderDetailVO.setMallOrderId(mallOrder.getMallOrderId());
        OrderDetailVO.ShopInfoBean shopInfoBean = new OrderDetailVO.ShopInfoBean();
        shopInfoBean.setShopId(shop.getId().toString());
        shopInfoBean.setShopName(shop.getShopName());
        shopInfoBean.setShopOrderId(shopOrderId);
        ItemOrder itemQueryParam = new ItemOrder();
        itemQueryParam.setShopOrderId(shopOrder.getShopOrderId());
        List<ItemOrder> itemOrders = iItemOrderService.selectByObjectList(itemQueryParam);
        List<OrderDetailVO.RelateProductsBean> relateProductsBeans = Lists.newArrayList();
        for (ItemOrder itemOrder : itemOrders) {
            OrderDetailVO.RelateProductsBean relateProductsBean = new OrderDetailVO.RelateProductsBean();
            Sku sku = skuService.selectByPrimaryKey(itemOrder.getSkuId());
            if (sku == null) {
                log.warn("商品不存在{}", itemOrder.getSkuId());
                return WrapMapper.error("商品不存在");
            }
            Item item = itemService.selectByPrimaryKey(sku.getItemId());
            if (item == null) {
                log.warn("商品不存在{}", sku.getItemId());
                return WrapMapper.error("商品不存在");
            }
            relateProductsBean.setImage(item.getTitleImg());
            relateProductsBean.setProductName(item.getItemTitle());
            relateProductsBean.setProductPrice(PriceFormat.formatStr(item.getPrice()));
            relateProductsBean.setSkuId(sku.getId().toString());
            relateProductsBean.setNum(itemOrder.getItemCount());
            relateProductsBean.setItemOrderId(itemOrder.getItemOrderId());
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
        OrderDetailVO.ReceiveUserBean receiveUserBean = new OrderDetailVO.ReceiveUserBean();
        receiveUserBean.setAddress(mallOrder.getDeliveryAddress());
        receiveUserBean.setConsigneeName(mallOrder.getDeliveryName());
        receiveUserBean.setConsigneePnone(mallOrder.getPhone());
        orderDetailVO.setReceiveUser(receiveUserBean);
        orderDetailVO.setOrderNo(shopOrder.getId().toString());
        orderDetailVO.setOrderCreateTime(shopOrder.getOrderCreateTime());
        orderDetailVO.setOrderPayTime(PriceFormat.formatStr(shopOrder.getPayPrice()));
        //todo 交货时间
        //orderDetailVO.setOrderDeliveryTime(mallOrder);
        //todo 退货时间
        //orderDetailVO.setOrderRefundTime();
        return WrapMapper.ok(orderDetailVO);
    }


    @PostMapping("/pay")
    @ApiOperation("支付")
    public Wrapper<Boolean> pay(@RequestBody @Valid @ApiParam PayDTO payDTO) {
        log.info("支付订单{}", payDTO);
        orderService.payOrder(payDTO);
        return WrapMapper.ok(true);
    }

}
