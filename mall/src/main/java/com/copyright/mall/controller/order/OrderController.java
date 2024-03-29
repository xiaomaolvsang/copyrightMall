package com.copyright.mall.controller.order;

import com.alibaba.fastjson.JSON;
import com.copyright.mall.bean.Item;
import com.copyright.mall.bean.ItemOrder;
import com.copyright.mall.bean.MallOrder;
import com.copyright.mall.bean.Shop;
import com.copyright.mall.bean.ShopOrder;
import com.copyright.mall.bean.Sku;
import com.copyright.mall.bean.UserAddress;
import com.copyright.mall.controller.BaseController;
import com.copyright.mall.domain.dto.cart.CreateOrderDTO;
import com.copyright.mall.domain.dto.order.ConfirmOrderParam;
import com.copyright.mall.domain.dto.order.CreateOrderParam;
import com.copyright.mall.domain.dto.order.PayDTO;
import com.copyright.mall.domain.dto.order.PreorderParam;
import com.copyright.mall.domain.dto.order.QueryOrderListParam;
import com.copyright.mall.domain.vo.order.ConfirmOrderVO;
import com.copyright.mall.domain.vo.order.CreateOrderVO;
import com.copyright.mall.domain.vo.order.OrderDetailVO;
import com.copyright.mall.domain.vo.order.OrderInfoVO;
import com.copyright.mall.enums.ShopOrderType;
import com.copyright.mall.service.IItemOrderService;
import com.copyright.mall.service.IItemService;
import com.copyright.mall.service.IMallOrderService;
import com.copyright.mall.service.IShopOrderService;
import com.copyright.mall.service.IShopService;
import com.copyright.mall.service.ISkuService;
import com.copyright.mall.service.IUserAddressService;
import com.copyright.mall.service.OrderService;
import com.copyright.mall.util.BeanMapperUtils;
import com.copyright.mall.util.PriceFormat;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.wxpay.sdk.MallWXPayConfig;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        log.info("confirmOrder=>{}", JSON.toJSONString(confirmOrderParam));
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
            Sku sku = skuService.selectByPrimaryKeyFromDBWithIncrSoldInventory(dtoItem.getSkuId() , dtoItem.getNum());
            if (sku == null) {
                log.warn("商品不存在{}", dtoItem.getSkuId());
                return WrapMapper.error("商品不存在");
            }
            if(sku.getInventory() < sku.getSoldInventory()){
                log.info("inventor insufficient skip");
                continue;
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
        if(CollectionUtils.isEmpty(productsBeans)){
            return WrapMapper.error("商品库存不足");
        }
        result.setProducts(productsBeans);
        result.setTotalPayPrice(PriceFormat.format(totalPrice));
        return WrapMapper.ok(result);

    }

    @PostMapping("/createOrder")
    @ApiOperation("生单")
    public Wrapper<CreateOrderVO> createOrder(@ApiParam @Valid @RequestBody CreateOrderParam createOrderParam) {
        log.info("createOrdrer=>{}", JSON.toJSONString(createOrderParam));
        CreateOrderDTO createOrderDTO = BeanMapperUtils.map(createOrderParam, CreateOrderDTO.class);
        createOrderDTO.setUserId(this.getUserId());
        createOrderDTO.setMallId(this.getMallId());
        MallOrder mallOrderId = orderService.createOrder(createOrderDTO);
        CreateOrderVO createOrderVO = new CreateOrderVO();
        createOrderVO.setOrderNo(mallOrderId.getMallOrderId());
        try {
            //调用微信预生单
            long now = System.currentTimeMillis()/1000;
            MallWXPayConfig wxPayConfig = new MallWXPayConfig();
            WXPay wxPay = new WXPay(wxPayConfig,"https://api.798ipartstore.com/v1/order/pay");
            Map<String, String> data = new HashMap<String, String>();
            data.put("body", "i");
            String out_trade_no = String.valueOf(mallOrderId.getMallOrderId());
            data.put("out_trade_no", out_trade_no);//商户订单号
            data.put("total_fee", mallOrderId.getPrice().toString());
            data.put("spbill_create_ip","182.92.128.239");
            data.put("notify_url", "https://api.798ipartstore.com/v1/order/pay");
            data.put("trade_type", "JSAPI");
            data.put("openid",this.getOpenId());
            Map<String, String> resp = Maps.newHashMap();
            try {
                log.info("wx pay data = {}", data);
                resp = wxPay.unifiedOrder(data);
                System.out.println(resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String nonceStr = resp.get("nonce_str");
            String prepayId = "prepay_id="+resp.get("prepay_id");
            createOrderVO.setNonceStr(nonceStr);
            createOrderVO.setPrepayId(prepayId);
            createOrderVO.setTimestamp(String.valueOf(now));
            createOrderVO.setSign(generateSign(now, nonceStr, prepayId));
        } catch (Exception e) {
            log.error("wx eception e = {}",e.getMessage(),e);
            return WrapMapper.error("微信预生单失败");
        }
        return WrapMapper.ok(createOrderVO);
    }

    @PostMapping("/preOrder")
    public Wrapper<CreateOrderVO> prePay(@ApiParam @Valid @RequestBody PreorderParam preorderParam){
        String orderId = preorderParam.getOrderId();
        if(!preorderParam.getOrderId().startsWith("SID")){
            return WrapMapper.error("订单号不合法");
        }
        CreateOrderVO createOrderVO = new CreateOrderVO();
        createOrderVO.setOrderNo(orderId);
        ShopOrder shopOrder = shopOrderService.selectByShopOrderId(preorderParam.getOrderId());
        try {
            long now = System.currentTimeMillis()/1000;
            //调用微信预生单
            MallWXPayConfig wxPayConfig = new MallWXPayConfig();
            WXPay wxPay = new WXPay(wxPayConfig,"https://api.798ipartstore.com/v1/order/pay");
            Map<String, String> data = new HashMap<String, String>();
            data.put("body", "i");
            data.put("out_trade_no", orderId);//商户订单号
            data.put("total_fee", shopOrder.getPrice().toString());
            data.put("spbill_create_ip","182.92.128.239");
            data.put("notify_url", "https://api.798ipartstore.com/v1/order/pay");
            data.put("trade_type", "JSAPI");  // 此处指定为扫码支付
            data.put("openid",this.getOpenId());
            Map<String, String> resp = Maps.newHashMap();
            try {
                log.info("wx pay data = {}", data);
                resp = wxPay.unifiedOrder(data);
                System.out.println(resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String nonceStr = resp.get("nonce_str");
            String prepayId = "prepay_id="+resp.get("prepay_id");
            createOrderVO.setNonceStr(nonceStr);
            createOrderVO.setPrepayId(prepayId);
            createOrderVO.setTimestamp(String.valueOf(now));
            createOrderVO.setSign(generateSign(now, nonceStr, prepayId));
        } catch (Exception e) {
            log.error("wx eception e = {}",e.getMessage(),e);
            return WrapMapper.error("微信预生单失败");
        }
        return WrapMapper.ok(createOrderVO);
    }


    @GetMapping("/unpaidOrderList")
    @ApiOperation("代付款订单列表")
    public Wrapper<PageInfo<OrderInfoVO.ShopInfoBean>> orderList(@ApiParam @Valid QueryOrderListParam queryOrderListParam) {
        MallOrder mallQueryParam = new MallOrder();
        mallQueryParam.setBuyer(this.getUserId().toString());
        mallQueryParam.setMallId(this.getMallId().toString());
        Page<MallOrder> page = PageHelper.startPage(queryOrderListParam.getPageNum(), queryOrderListParam.getPageSize());
        List<MallOrder> mallOrders = mallOrderService.selectByObjectList(mallQueryParam);
        List<OrderInfoVO> orderInfoVOS = Lists.newArrayList();
        for (MallOrder mallOrder : mallOrders) {
            ShopOrder queryParam = new ShopOrder();
            queryParam.setMallOrderId(mallOrder.getMallOrderId());
            if(queryOrderListParam.getOrderStatus()!=-1){
                queryParam.setOrderType(queryOrderListParam.getOrderStatus());
            }
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
                if(queryOrderListParam.getOrderStatus()!=-1) {
                    itemQueryParam.setItemOrderStatus(queryOrderListParam.getOrderStatus());
                }
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
                    relateProductsBean.setProductPrice(PriceFormat.format(itemOrder.getItemPrice()));
                    relateProductsBean.setSkuId(sku.getId().toString());
                    relateProductsBean.setNum(itemOrder.getItemCount());
                    relateProductsBean.setItemOrderId(itemOrder.getItemOrderId());
                    relateProductsBeanArrayList.add(relateProductsBean);
                }
                shopInfoBean.setRelateProducts(relateProductsBeanArrayList);
                shopInfoBean.setShopId(shop.getId().toString());
                shopInfoBean.setShopName(shop.getShopName());
                shopInfoBean.setOrderStatus(shopOrder.getOrderType().toString());
                shopInfoBean.setStatusDesc(ShopOrderType.of(shopOrder.getOrderType()).getDesc());
                shopInfoBean.setPayPrice(PriceFormat.format(shopOrder.getPrice()));
                shopInfoBeans.add(shopInfoBean);
            }
            orderInfoVO.setShopInfo(shopInfoBeans);
            orderInfoVOS.add(orderInfoVO);
        }
        List<OrderInfoVO.ShopInfoBean> shopInfoBeans = new ArrayList<>();
        for(OrderInfoVO orderInfoVO : orderInfoVOS){
            shopInfoBeans.addAll(orderInfoVO.getShopInfo());
        }
        PageInfo<OrderInfoVO.ShopInfoBean> result = PageInfo.of(shopInfoBeans);
        result.setTotal(page.getTotal());
        return WrapMapper.ok(result);
    }
    @GetMapping("/orderDetail/{shopOrderId}")
    @ApiOperation("订单详情")
    public Wrapper<OrderDetailVO> getOrderDetail(@PathVariable("shopOrderId") String shopOrderId) {
        if(!shopOrderId.startsWith("SID")){
            return WrapMapper.error("订单号不合法");
        }
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
            relateProductsBean.setProductPrice(PriceFormat.formatStr(sku.getPrice()));
            relateProductsBean.setSkuId(sku.getId().toString());
            relateProductsBean.setNum(itemOrder.getItemCount());
            relateProductsBean.setItemOrderId(itemOrder.getItemOrderId());
            relateProductsBeans.add(relateProductsBean);
        }
        shopInfoBean.setRelateProducts(relateProductsBeans);
        orderDetailVO.setShopInfo(shopInfoBean);
        orderDetailVO.setStatus(shopOrder.getOrderType().toString());
        orderDetailVO.setStatusDesc(ShopOrderType.of(shopOrder.getOrderType()).getDesc());
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
        orderDetailVO.setOrderNo(shopOrder.getShopOrderId());
        orderDetailVO.setOrderCreateTime(shopOrder.getOrderCreateTime());
        orderDetailVO.setOrderPayTime(shopOrder.getPayTime());
        orderDetailVO.setShopOrderPrice(PriceFormat.format(shopOrder.getPrice()));
        return WrapMapper.ok(orderDetailVO);
    }


    @PostMapping("/pay")
    @ApiOperation("支付")
    public String pay(@RequestBody @Valid @ApiParam String body) throws Exception {
        log.info("支付订单{}", body);
        Map<String,String> data = Maps.newHashMap();
        try {
            data = WXPayUtil.xmlToMap(body);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PayDTO payDTO = new PayDTO();
        payDTO.setOrderId(String.valueOf((data.get("out_trade_no"))));
        orderService.payOrder(payDTO);
        Map<String,String> map = new HashMap<>();
        map.put("return_code","SUCCESS");
        map.put("return_msg","OK");
        return WXPayUtil.mapToXml(map);
    }

    private static String generateSign(long timeStamp,String nonceStr,String packageStr){
        Map<String, String> data = Maps.newHashMap();
        data.put("appId","wx71dfe9d7cf33f2d2");
        data.put("timeStamp",String.valueOf(timeStamp));
        data.put("nonceStr",nonceStr);
        data.put("package",packageStr);
        data.put("signType","MD5");
        try {
            return WXPayUtil.generateSignature(data,"798art798whgs798qsdgfdHGSDAdgsvb",WXPayConstants.SignType.MD5);
        } catch (Exception e) {
            return "";
        }
    }



    public static void main(String[] args) throws Exception {
        System.out.println(        generateSign(1490840662,"5K8264ILTKCH16CQ2502SI8ZNMTM67VS","prepay_id=wx2017033010242291fcfe0db70013231072"));    }
}
