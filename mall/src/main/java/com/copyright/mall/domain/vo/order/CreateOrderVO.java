package com.copyright.mall.domain.vo.order;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : zhangyuchen
 * @date : 2019/11/23 11:36
 */
@Data
public class CreateOrderVO {


    @JSONField(name = "orderNo")
    @ApiModelProperty("订单号")
    private String orderNo;


    private String nonceStr;

    @JSONField(name = "package")
    private String prepayId;

    private String sign;

    private String timestamp;
}
