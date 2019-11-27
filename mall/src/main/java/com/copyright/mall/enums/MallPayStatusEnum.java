package com.copyright.mall.enums;

/**
 * @author : zhangyuchen
 * @date : 2019/11/27 15:03
 */
public enum MallPayStatusEnum {
    /**
     * 支付状态枚举
     */
    UNPAID(10,"未支付"),
    PAID(20,"已支付");

    private Integer code;
    private String desc;

    MallPayStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public MallPayStatusEnum valueOf(Integer code){
        for(MallPayStatusEnum payStatusEnum : MallPayStatusEnum.values()){
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
