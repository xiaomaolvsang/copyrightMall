package com.copyright.mall.manage.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.copyright.mall.bean.ShopOrder;
import com.copyright.mall.domain.export.ShopOrderExport;
import com.copyright.mall.domain.vo.order.OrderInfoVO;
import com.copyright.mall.enums.ShopOrderType;
import com.copyright.mall.manage.domain.dto.ExportOrderParam;
import com.copyright.mall.manage.domain.dto.ModifyPriceParam;
import com.copyright.mall.manage.domain.dto.QueryOrderListParam;
import com.copyright.mall.manage.domain.dto.ShipParam;
import com.copyright.mall.manage.domain.dto.ShopItemOrderDetail;
import com.copyright.mall.manage.domain.dto.ShopOrderDetail;
import com.copyright.mall.manage.domain.vo.ShopOrderInfo;
import com.copyright.mall.service.OrderService;
import com.copyright.mall.service.impl.ShopOrderService;
import com.copyright.mall.util.BeanMapperUtils;
import com.copyright.mall.util.PriceFormat;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author zhangyuchen
 */
@Api(tags = "后端管理订单接口")
@Slf4j
@RestController
@RequestMapping("/manage/v1/order")
public class ManageOrderController extends BaseManageController {

    @Resource
    private OrderService orderService;

    @Resource
    private ShopOrderService shopOrderService;

    @Resource
    private HttpServletResponse response;

    @GetMapping("/list")
    @ApiOperation("订单列表")
    public Wrapper<PageInfo<ShopOrderInfo>> listOrder(@ApiParam @Valid QueryOrderListParam queryOrderListParam) {
        log.info("queryOrderListParam = {}", queryOrderListParam);
        Page<ShopOrderDetail> page = PageHelper.startPage(
                queryOrderListParam.getPageNum(), queryOrderListParam.getPageSize());
        List<ShopOrderDetail> shopOrders = shopOrderService.selectShopOrder(queryOrderListParam);
        List<ShopOrderInfo> shopOrderInfos = toShopOrderInfo(shopOrders);
        PageInfo<ShopOrderInfo> shopOrderInfoPageInfo = PageInfo.of(shopOrderInfos);
        BeanUtils.copyProperties(page, shopOrderInfoPageInfo);
        shopOrderInfoPageInfo.setList(shopOrderInfos);
        return WrapMapper.ok(shopOrderInfoPageInfo);
    }

    @PostMapping("/cancelOrder/{orderId}")
    @ApiOperation("取消订单")
    public Wrapper<Boolean> cancelOrder(@ApiParam("订单号") @Valid @PathVariable("orderId") String orderId) {
        orderService.cancelOrder(orderId);
        return WrapMapper.ok(true);
    }

    @PostMapping("/modifyPrice")
    @ApiOperation("修改订单")
    public Wrapper<Boolean> modifyPrice(@ApiParam @Valid @RequestBody ModifyPriceParam modifyPriceParam) {
        log.info("modifyPriceParam = {}", modifyPriceParam);
        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setShopOrderId(modifyPriceParam.getOrderId());
        orderService.modifyOrder(modifyPriceParam);
        return WrapMapper.ok(true);
    }

    @PostMapping("/ship")
    @ApiOperation("发货")
    public Wrapper<Boolean> ship(@ApiParam @Valid @RequestBody ShipParam shipParam) {
        log.info("shipParam = {}", shipParam);
        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setShopOrderId(shipParam.getOrderId());
        shopOrder.setDeliveryOrderId(shipParam.getTrackingNumber());
        shopOrder.setDelliveryCompanyName(shipParam.getCompanyName());
        shopOrder.setOrderType(ShopOrderType.SHIPPED.getCode());
        shopOrderService.modifyByShopOrderId(shopOrder);
        return WrapMapper.ok(true);
    }

    @PostMapping("/export")
    @ApiOperation("导出订单")
    public void exportOrder(@ApiParam @Valid @RequestBody ExportOrderParam exportOrderParam) throws IOException {
        QueryOrderListParam queryOrderListParam = BeanMapperUtils.map(exportOrderParam, QueryOrderListParam.class);
        List<ShopItemOrderDetail> shopOrders = orderService.orderDetailList(queryOrderListParam);
        List<ShopOrderExport> shopOrderExports = Lists.newArrayList();
        for (ShopItemOrderDetail shopItemOrderDetail : shopOrders) {
            ShopOrderExport shopOrderExport = new ShopOrderExport();
            shopOrderExport.setShopId(shopItemOrderDetail.getShopId());
            shopOrderExport.setShopName(shopItemOrderDetail.getShopName());
            shopOrderExport.setOrderStatus(shopItemOrderDetail.getOrderStatus());
            shopOrderExport.setStatusDesc(ShopOrderType.of(Integer.valueOf(shopItemOrderDetail.getOrderStatus())).getDesc());
            shopOrderExport.setPayPrice(PriceFormat.format(shopItemOrderDetail.getPayPrice()));
            shopOrderExport.setShopOrderId(shopItemOrderDetail.getShopOrderId());
            shopOrderExport.setImage(shopItemOrderDetail.getImage());
            shopOrderExport.setItemOrderId(shopItemOrderDetail.getItemOrderId());
            shopOrderExport.setProductName(shopItemOrderDetail.getProductName());
            shopOrderExport.setProductPrice(PriceFormat.format(shopItemOrderDetail.getProductPrice()));
            shopOrderExport.setSkuId(shopItemOrderDetail.getSkuId());
            shopOrderExport.setNum(shopItemOrderDetail.getNum());
            shopOrderExports.add(shopOrderExport);
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = new String((System.currentTimeMillis() + ".xlsx").getBytes(), StandardCharsets.UTF_8);
        response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
        EasyExcel.write(response.getOutputStream(), ShopOrderExport.class)
                .sheet(0, "订单列表明细")
                .doWrite(shopOrderExports);
    }

    public List<ShopOrderInfo> toShopOrderInfo(List<ShopOrderDetail> shopOrders) {
        List<ShopOrderInfo> shopOrderInfos = Lists.newArrayList();
        for (ShopOrderDetail shopOrderDetail : shopOrders) {
            ShopOrderInfo shopOrderInfo = new ShopOrderInfo();
            shopOrderInfo.setOrderStatus(shopOrderDetail.getOrderStatus());
            shopOrderInfo.setStatusDesc(ShopOrderType.of(Integer.parseInt(shopOrderDetail.getOrderStatus())).getDesc());
            shopOrderInfo.setPayPrice(PriceFormat.format(shopOrderDetail.getPayPrice()));
            shopOrderInfo.setShopId(shopOrderDetail.getShopId());
            shopOrderInfo.setShopName(shopOrderDetail.getShopName());
            shopOrderInfo.setShopOrderId(shopOrderDetail.getShopOrderId());
            List<OrderInfoVO.RelateProductsBean> relateProductsBeans = Lists.newArrayList();
            for (ShopOrderDetail.ItemOrder itemOrder : shopOrderDetail.getItemOrders()) {
                OrderInfoVO.RelateProductsBean relateProductsBean = new OrderInfoVO.RelateProductsBean();
                relateProductsBean.setImage(itemOrder.getImage());
                relateProductsBean.setItemOrderId(itemOrder.getItemOrderId());
                relateProductsBean.setNum(itemOrder.getNum());
                relateProductsBean.setProductName(itemOrder.getProductName());
                relateProductsBean.setProductPrice(PriceFormat.format(itemOrder.getProductPrice()));
                relateProductsBean.setSkuId(itemOrder.getSkuId());
                relateProductsBeans.add(relateProductsBean);
            }
            shopOrderInfo.setRelateProducts(relateProductsBeans);
            shopOrderInfos.add(shopOrderInfo);
        }
        return shopOrderInfos;
    }
}
