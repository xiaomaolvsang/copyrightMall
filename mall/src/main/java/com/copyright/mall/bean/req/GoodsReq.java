package com.copyright.mall.bean.req;

import java.io.Serializable;

/**
 * GoodsListReq
 *
 * @author lijian
 * @version 1.0
 * @date 2019/10/10 4:56 下午
 */
public class GoodsReq implements Serializable {
  private String itemTitle;
  private Long shopId;
  private String itemClass;
  private int pageNo;
  private int pageSize;

  public String getItemTitle() {
    return itemTitle;
  }

  public void setItemTitle(String itemTitle) {
    this.itemTitle = itemTitle;
  }

  public Long getShopId() {
    return shopId;
  }

  public void setShopId(Long shopId) {
    this.shopId = shopId;
  }

  public String getItemClass() {
    return itemClass;
  }

  public void setItemClass(String itemClass) {
    this.itemClass = itemClass;
  }

  public int getPageNo() {
    return pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
}
