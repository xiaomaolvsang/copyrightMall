package com.copyright.mall.manage.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("商城回参")
public class MallResp {
    /**主键**/
    @ApiModelProperty("商城id")
    private Long id;

    /**创建时间**/
    @ApiModelProperty("创建时间")
    private Date gmtCreate;

    /**修改时间**/
    @ApiModelProperty("修改时间")
    private Date gmtModified;

    /**商品名称**/
    @ApiModelProperty("商城名称")
    private String mallName;

    /**商品logo**/
    @ApiModelProperty("商城logo")
    private String mallLogo;

    /**单位名**/
    @ApiModelProperty("单位名")
    private String companyName;

    /**邮箱**/
    @ApiModelProperty("邮箱")
    private String contactUs;

    /**是否认证（0未认证-1认证）**/
    @ApiModelProperty("是否认证（0未认证-1认证）")
    private Integer isIdentification;
}
