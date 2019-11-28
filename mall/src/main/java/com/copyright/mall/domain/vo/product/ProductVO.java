package com.copyright.mall.domain.vo.product;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * ProductVO
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/28 3:56 下午
 */
@ApiModel
@Data
public class ProductVO {

  /**
   * data : {"productImage":"","productName":"","productPrice":"","productId":"","recommend":[{"image":"","institutionName":"","productName":"","productPrice":"","productId":""}],"institution":{"institutionAvatar":"","institutionName":"","institutionId":""},"descImage":[{"image":""}]}
   */

  private DataBean data;

  @Data
  public static class DataBean {
    /**
     * productImage :
     * productName :
     * productPrice :
     * productId :
     * recommend : [{"image":"","institutionName":"","productName":"","productPrice":"","productId":""}]
     * institution : {"institutionAvatar":"","institutionName":"","institutionId":""}
     * descImage : [{"image":""}]
     */

    private String productImage;
    private String productName;
    private String productPrice;
    private Long productId;
    private InstitutionBean institution;
    private List<RecommendBean> recommend;
    private List<DescImageBean> descImage;

    @Data
    public static class InstitutionBean {
      /**
       * institutionAvatar :
       * institutionName :
       * institutionId :
       */

      private String institutionAvatar;
      private String institutionName;
      private Long institutionId;

    }

    @Data
    public static class RecommendBean {
      /**
       * image :
       * institutionName :
       * productName :
       * productPrice :
       * productId :
       */

      private String image;
      private String institutionName;
      private String productName;
      private String productPrice;
      private Long productId;
    }

    @Data
    public static class DescImageBean {
      /**
       * image :
       */

      private String image;
    }
  }
}
