package com.copyright.mall.manage.controller;


import com.copyright.mall.manage.domain.dto.ExportOrderParam;
import com.copyright.mall.manage.domain.dto.ModifyPriceParam;
import com.copyright.mall.manage.domain.dto.QueryOrderListParam;
import com.copyright.mall.manage.domain.dto.ShipParam;
import com.copyright.mall.manage.domain.vo.QueryOrderListRes;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author zhangyuchen
 */
@Api(tags = "后端管理订单接口")
@Slf4j
@RestController
@RequestMapping("/manage/v1/order")
public class ManageOrderController {


    @GetMapping("/list")
    @ApiOperation("订单列表")
    public Wrapper<PageInfo<QueryOrderListRes>> listOrder(@ApiParam @Valid @RequestBody QueryOrderListParam queryOrderListParam) {
        log.info("queryOrderListParam = {}", queryOrderListParam);
        return WrapMapper.ok();
    }

    @PostMapping("/cancelOrder/{orderId}")
    @ApiOperation("取消订单")
    public Wrapper<Boolean> cancelOrder(@ApiParam("订单号") @Valid @PathVariable("orderId") String orderId) {
        return WrapMapper.ok(true);
    }

    @PostMapping("/modifyPrice")
    @ApiOperation("修改订单")
    public Wrapper<Boolean> modifyPrice(@ApiParam @Valid @RequestBody ModifyPriceParam modifyPriceParam) {
        log.info("modifyPriceParam = {}", modifyPriceParam);
        return WrapMapper.ok(true);
    }

    @PostMapping("/ship")
    @ApiOperation("发货")
    public Wrapper<Boolean> ship(@ApiParam @Valid @RequestBody ShipParam shipParam) {
        log.info("shipParam = {}", shipParam);
        return WrapMapper.ok(true);
    }

    @PostMapping("/export")
    @ApiOperation("导出订单")
    public void exportOrder(@ApiParam @Valid @RequestBody ExportOrderParam exportOrderParam){
        log.info("导出订单参数");
    }
}
