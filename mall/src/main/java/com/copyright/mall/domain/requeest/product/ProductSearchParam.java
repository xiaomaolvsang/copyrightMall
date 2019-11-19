package com.copyright.mall.domain.requeest.product;

import com.copyright.mall.bean.page.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;

/**
 * ProductParam
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/17 2:30 下午
 */
@Data
@ApiModel("product搜索入参")
public class ProductSearchParam extends Page {

  @ApiModelProperty("商城id")
  @NotBlank(message = "商城id不能为空")
  private Long mallId;

  @ApiModelProperty("搜索关键字")
  @NotBlank(message = "关键字不能为空")
  private String keyword;

  @ApiModelProperty("搜索类型（product/artist）")
  @NotBlank(message = "搜索类型不能为空")
  private String type;

  public Long getMallId() {
    return mallId;
  }

  public void setMallId(Long mallId) {
    this.mallId = mallId;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public static Boolean paramChecking(ProductSearchParam productSearchParam){

      return (productSearchParam.getMallId() == null ||
        StringUtils.isEmpty(productSearchParam.getKeyword()) ||
        StringUtils.isEmpty(productSearchParam.getType()));
  }
}
