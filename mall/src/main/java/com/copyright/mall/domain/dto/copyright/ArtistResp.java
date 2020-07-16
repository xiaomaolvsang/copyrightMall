package com.copyright.mall.domain.dto.copyright;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("艺术家resp")
public class ArtistResp {
    @ApiModelProperty("正式名称")
    private String name;
    @ApiModelProperty("昵称")
    private String petName;
    @ApiModelProperty("电话")
    private String phone;
    @ApiModelProperty("标签")
    private String shopArtCategory;
    @ApiModelProperty("头像")
    private String logo;
    @ApiModelProperty("代表作品")
    private String opusImg;
    @ApiModelProperty("简介")
    private String certification;
    @ApiModelProperty("艺术家id")
    private Long id;
    @ApiModelProperty("证件照")
    private String idCardImg;
}
