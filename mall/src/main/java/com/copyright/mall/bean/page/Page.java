package com.copyright.mall.bean.page;

/**
 * Page
 *
 * @author lijian
 * @version 1.0
 * @date 2019/10/10 5:17 下午
 */
public class Page {
  private int startOfPage = 0;
  private int pageSize = 10;
  private int pageNo = 1;
  private int total;

  public int getStartOfPage() {
    return startOfPage;
  }

  public void setStartOfPage(int startOfPage) {
    this.startOfPage = startOfPage;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getPageNo() {
    return pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }
}
