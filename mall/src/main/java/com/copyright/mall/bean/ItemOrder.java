package com.copyright.mall.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 子订单
 *
 * @author lijian
 * @date 2019-10-10 16:30
 **/
@Data
public class ItemOrder implements Serializable {

    private static final long serialVersionUID = 5331428971875366225L;

    /**
     * 主键
     **/
    private Long id;

    /**
     * 创建时间
     **/
    private Date gmtCreate;

    /**
     * 修改时间
     **/
    private Date gmtModified;

    /**
     * 子订单号
     **/
    private String itemOrderId;

    /**
     * 商铺订单号
     **/
    private String shopOrderId;

    /**
     * 商品id
     **/
    private Long itemId;

    /**
     * sku号
     **/
    private Long skuId;

    /**
     * 商品订单状态
     **/
    private Integer itemOrderStatus;

    /**
     * 商品价格
     **/
    private Integer itemPrice;

    /**
     * 商品数量
     **/
    private Integer itemCount;

    /**
     * 商品总价
     **/
    private Integer itemTotalPrice;
}
