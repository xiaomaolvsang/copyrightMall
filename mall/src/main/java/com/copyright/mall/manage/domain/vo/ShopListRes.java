package com.copyright.mall.manage.domain.vo;

import com.copyright.mall.domain.dto.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
@ApiModel("商铺列表")
public class ShopListRes extends BasePage {
    @ApiModelProperty("商铺名称")
    private String shopName;

    @ApiModelProperty("商铺logo")
    private String shopLogo;

    @ApiModelProperty("商铺类型（0-sale,1-artist）")
    private Integer shopType;

    @ApiModelProperty("单位名")
    private String companyName;

    @ApiModelProperty("pdf")
    private String certification;

    @ApiModelProperty("商铺图片")
    private String shopImg;

    @ApiModelProperty("艺术家品类")
    private String shopArtCategory;

    @ApiModelProperty("是否认证（0未认证-1认证）")
    private Integer isIdentification;

    @ApiModelProperty("商铺id")
    private Long shopId;

    @ApiModelProperty("用户列表")
    private List<User> users;

    @Data
    public static class User{
        @ApiModelProperty("用户id")
        private Long userId;

        @ApiModelProperty("电话")
        private String phone;
    }
}
