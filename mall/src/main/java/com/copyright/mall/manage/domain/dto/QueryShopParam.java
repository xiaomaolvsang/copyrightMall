package com.copyright.mall.manage.domain.dto;


import com.copyright.mall.domain.dto.BasePage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Api("商铺查询接口参数")
@Data
public class QueryShopParam extends BasePage {
    private Long userId;
    @ApiModelProperty("商铺id")
    private Long shopId;
    @ApiModelProperty("商铺名称")
    private String shopName;
    @ApiModelProperty("商铺类型")
    private Integer shopType;
}
