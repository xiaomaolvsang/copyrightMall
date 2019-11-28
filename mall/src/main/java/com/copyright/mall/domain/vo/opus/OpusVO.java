package com.copyright.mall.domain.vo.opus;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * Opus
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/28 5:15 下午
 */
@Data
@ApiModel
public class OpusVO {

  /**
   * data : {"artistAvatar":"","artistName":"","productImage":[{"image":""}],"publishTime":"","productTitle":"","productDesc":""}
   */

  private DataBean data;

  @Data
  public static class DataBean {
    /**
     * artistAvatar :
     * artistName :
     * productImage : [{"image":""}]
     * publishTime :
     * productTitle :
     * productDesc :
     */

    private String artistAvatar;
    private String artistName;
    private String publishTime;
    private String productTitle;
    private String productDesc;
    private List<ProductImageBean> productImage;

    @Data
    public static class ProductImageBean {
      /**
       * image :
       */

      private String image;
    }
  }
}
