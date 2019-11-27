package com.copyright.mall.domain.requeest.product;

import com.copyright.mall.domain.dto.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * ProductAreaParam
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/24 3:44 下午
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("productAreaParam入参")
public class AreaParam extends BasePage {
    @ApiModelProperty("商城与版权（productArea/copyrightArea）")
    @NotBlank(message = "商城与版权不能为空")
    private String type;
    @ApiModelProperty("商城id")
    @NotNull(message = "mallId不能为空")
    private Long mallId;
}
