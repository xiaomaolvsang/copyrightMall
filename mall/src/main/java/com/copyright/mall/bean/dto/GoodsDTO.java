package com.copyright.mall.bean.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * GoodsDTO
 *
 * @author lijian
 * @version 1.0
 * @date 2019/10/10 5:06 下午
 */
public class GoodsDTO implements Serializable {
  private static final long serialVersionUID = 3925294372488782877L;

  /**创建时间**/
  private Date gmtCreate;

  /**修改时间**/
  private Date gmtModified;

  /**商铺主键**/
  private Long shopId;

  /**商品code**/
  private String barcode;

  /**品类**/
  private String itemClass;

  /****/
  private String relatedCopyright;

  /**商品状态 1上架 0下架**/
  private Integer itemStatus;

  /**商品名称**/
  private String itemTitle;

  /****/
  private String ad;

  /**商品图片**/
  private String titleImg;

  /**商品描述图片**/
  private String contentImg;


  public void setGmtCreate(Date gmtCreate){
    this.gmtCreate = gmtCreate;
  }

  public Date getGmtCreate(){
    return this.gmtCreate;
  }

  public void setGmtModified(Date gmtModified){
    this.gmtModified = gmtModified;
  }

  public Date getGmtModified(){
    return this.gmtModified;
  }

  public void setShopId(Long shopId){
    this.shopId = shopId;
  }

  public Long getShopId(){
    return this.shopId;
  }

  public void setBarcode(String barcode){
    this.barcode = barcode;
  }

  public String getBarcode(){
    return this.barcode;
  }

  public void setItemClass(String itemClass){
    this.itemClass = itemClass;
  }

  public String getItemClass(){
    return this.itemClass;
  }

  public void setRelatedCopyright(String relatedCopyright){
    this.relatedCopyright = relatedCopyright;
  }

  public String getRelatedCopyright(){
    return this.relatedCopyright;
  }

  public void setItemStatus(Integer itemStatus){
    this.itemStatus = itemStatus;
  }

  public Integer getItemStatus(){
    return this.itemStatus;
  }

  public void setItemTitle(String itemTitle){
    this.itemTitle = itemTitle;
  }

  public String getItemTitle(){
    return this.itemTitle;
  }

  public void setAd(String ad){
    this.ad = ad;
  }

  public String getAd(){
    return this.ad;
  }

  public void setTitleImg(String titleImg){
    this.titleImg = titleImg;
  }

  public String getTitleImg(){
    return this.titleImg;
  }

  public void setContentImg(String contentImg){
    this.contentImg = contentImg;
  }

  public String getContentImg(){
    return this.contentImg;
  }

  @Override
  public String toString() {
    return "Item [ gmtCreate= "+gmtCreate+
      ",gmtModified= "+gmtModified+
      ",shopId= "+shopId+
      ",barcode= "+barcode+
      ",itemClass= "+itemClass+
      ",relatedCopyright= "+relatedCopyright+
      ",itemStatus= "+itemStatus+
      ",itemTitle= "+itemTitle+
      ",ad= "+ad+
      ",titleImg= "+titleImg+
      ",contentImg= "+contentImg+"]";
  }
}
