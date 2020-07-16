package com.copyright.mall.domain.requeest.opus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * OpusParam
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/28 5:14 下午
 */
@Data
@ApiModel("作品详情入参")
public class OpusParam {
  @ApiModelProperty("商铺id")
  private Long shopId;
  @ApiModelProperty("版权id")
  @NotNull(message = "opusId不能为空")
  private Long opusId;

  private Long userId;
}
