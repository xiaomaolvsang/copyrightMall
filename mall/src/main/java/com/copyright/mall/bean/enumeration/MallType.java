package com.copyright.mall.bean.enumeration;

/**
 * MallType
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/24 1:46 下午
 */
public enum MallType {
  /**商城**/
  mall,
  /**产权**/
  copyright;

  public static Boolean ifMallType(String source){
    for(MallType mallType : MallType.values()){
      if(mallType.name().equals(source)){
        return true;
      }
    }
    return false;
  }
}
