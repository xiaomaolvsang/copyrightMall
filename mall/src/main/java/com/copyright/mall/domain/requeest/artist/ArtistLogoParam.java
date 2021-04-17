package com.copyright.mall.domain.requeest.artist;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("艺术家logoParam")
public class ArtistLogoParam {
    @ApiModelProperty("头像")
    private String logo;
    @ApiModelProperty("代表作品")
    private String opusImg;
}
