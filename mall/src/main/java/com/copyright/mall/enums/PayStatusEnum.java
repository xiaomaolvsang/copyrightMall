package com.copyright.mall.enums;

/**
 * @author : zhangyuchen
 * @date : 2019/11/27 15:03
 */
public enum PayStatusEnum {
    /**
     * 支付状态枚举
     */
    UNPAID(1,"未支付");

    private Integer code;
    private String desc;

    PayStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public PayStatusEnum valueOf(Integer code){
        for(PayStatusEnum payStatusEnum : PayStatusEnum.values()){
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
