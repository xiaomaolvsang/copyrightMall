package com.copyright.mall.manage.domain.dto;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Api("创建修改商铺接口参数")
@Data
public class ModifyShopParam {

    @ApiModelProperty("商铺名称")
    private String shopName;

    @ApiModelProperty("商铺logo")
    private String shopLogo;

    @ApiModelProperty("商铺类型（0-sale,1-artist）")
    private String shopType;

    @ApiModelProperty("单位名")
    private String companyName;

    @ApiModelProperty("pdf")
    private String certification;

    @ApiModelProperty("商铺图片")
    private String shopImg;

    @ApiModelProperty("艺术家品类")
    private String shopArtCategory;

    @ApiModelProperty("品类")
    private String isIdentification;

    @ApiModelProperty("商铺id")
    private String shopId;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("密码")
    private String passWord;

}
