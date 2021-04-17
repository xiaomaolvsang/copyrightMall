package com.copyright.mall.enums;


public enum CopyRightStatusEnum {
    /**
     * 支付状态枚举
     */
    APPLYING(10,"申请中"),
    REJECTED(20,"已驳回"),
    CONFIRMED_RIGHT(30,"已受区块链保护"),
    AUTHORIZED(40,"已授权"),
    REVOKED(-10,"已撤销"),
    ;


    private Integer code;
    private String desc;

    CopyRightStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CopyRightStatusEnum valueOf(Integer code){
        for(CopyRightStatusEnum payStatusEnum : CopyRightStatusEnum.values()){
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
