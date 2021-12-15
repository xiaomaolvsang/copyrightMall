package com.copyright.mall.domain.dto.artist;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("艺术家头像及代表作")
public class ArtistLogo {
    @ApiModelProperty("头像")
    private String logo;
    @ApiModelProperty("代表作品")
    private String opusImg;
    @ApiModelProperty("昵称")
    private String artistName;
}
