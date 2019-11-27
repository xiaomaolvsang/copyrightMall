package com.copyright.mall.domain.requeest.product;

import com.copyright.mall.domain.dto.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * ProductByClass
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/25 7:41 下午
 */
@Data
@ApiModel("根据分类查商品入参")
public class ProductByClassparam extends BasePage {
    @ApiModelProperty("商城Id")
    @NotNull(message = "商城Id不能为空")
    private Long mallId;
    @ApiModelProperty("分类Id")
    @NotBlank(message = "分类id不能为空")
    private String firstCategoryId;
}
