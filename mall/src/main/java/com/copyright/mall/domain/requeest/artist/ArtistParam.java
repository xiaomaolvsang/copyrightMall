package com.copyright.mall.domain.requeest.artist;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("艺术家param")
public class ArtistParam {
    @ApiModelProperty("正式名称")
    @NotBlank(message = "正式名称不能为空")
    private String name;
    @ApiModelProperty("昵称")
    @NotBlank(message = "昵称不能为空")
    private String petName;
    @ApiModelProperty("电话")
    @NotBlank(message = "电话不能为空")
    private String phone;
    @ApiModelProperty("标签")
    private String shopArtCategory;
    @ApiModelProperty("头像")
    private String logo;
    @ApiModelProperty("代表作品")
    private String opusImg;
    @ApiModelProperty("简介")
    private String certification;
    @ApiModelProperty("我自己取")
    private String userId;
    @ApiModelProperty("证件照")
    private String idCardImg;
}
