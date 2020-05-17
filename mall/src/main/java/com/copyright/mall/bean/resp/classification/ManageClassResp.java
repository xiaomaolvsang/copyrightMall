package com.copyright.mall.bean.resp.classification;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("b端分类返回参数")
public class ManageClassResp {
    /**主键**/
    @ApiModelProperty("分类id")
    private Long id;

    /**分类名称**/
    @ApiModelProperty("分类名称")
    private String className;

    /**一二级分类 一级分类为0**/
    @ApiModelProperty("父分类id(一级分类为0)")
    private Long upperId;

    /**商城id**/
    @ApiModelProperty("商城id")
    private Long mallId;
}
