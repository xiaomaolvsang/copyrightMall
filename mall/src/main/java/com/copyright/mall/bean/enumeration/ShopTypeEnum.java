package com.copyright.mall.bean.enumeration;

/**
 * ShopTypeEnum
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/20 2:18 下午
 */
public enum ShopTypeEnum {
  product(0,"product"),
  artist(1,"artist");

  private int code;
  private String name;
  ShopTypeEnum(int code,String name){
    this.code = code;
    this.name = name;
  }

  public static ShopTypeEnum getTypeByName(String source){
    for(ShopTypeEnum shopTypeEnum : ShopTypeEnum.values()){
      if(shopTypeEnum.name.equals(source)){
          return shopTypeEnum;
      }
    }
    return null;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
