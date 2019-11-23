package com.copyright.mall.controller.order;

import com.copyright.mall.controller.BaseController;
import com.copyright.mall.domain.dto.order.ConfirmOrderParam;
import com.copyright.mall.domain.dto.order.CreateOrderParam;
import com.copyright.mall.domain.dto.order.QueryOrderListParam;
import com.copyright.mall.domain.vo.order.ConfirmOrderVO;
import com.copyright.mall.domain.vo.order.CreateOrderVO;
import com.copyright.mall.domain.vo.order.OrderDetailVO;
import com.copyright.mall.domain.vo.order.OrderInfoVO;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author : zhangyuchen
 * @date : 2019/11/23 11:21
 */
@Api(tags = "订单接口")
@Slf4j
@RestController
@RequestMapping("/v1/order")
public class OrderController extends BaseController {

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
        return WrapMapper.ok();
    }

    @GetMapping("/orderDetail/{orderNo}")
    @ApiOperation("/订单详情")
    public Wrapper<OrderDetailVO> getOrderDetail(@PathVariable("orderNo") String orderNo){
        return WrapMapper.ok();
    }

}
