package com.copyright.mall.enums;

/**
 * @author : zhangyuchen
 * @date : 2019/11/27 15:21
 */
public enum ShopOrderType {
    /**
     * 支付状态枚举
     */
    UNPAID(1,"未支付");


    private Integer code;
    private String desc;

    ShopOrderType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public ShopOrderType valueOf(Integer code){
        for(ShopOrderType payStatusEnum : ShopOrderType.values()){
            if(payStatusEnum.getCode().equals(code)){
                return payStatusEnum;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}