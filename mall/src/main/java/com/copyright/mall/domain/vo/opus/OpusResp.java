package com.copyright.mall.domain.vo.opus;

import com.copyright.mall.domain.dto.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("作品列表")
public class OpusResp extends BasePage {
    @ApiModelProperty("作平图片")
    private String opusImg;
    @ApiModelProperty("作品标题")
    private String opusTitle;
    @ApiModelProperty("艺术家头像")
    private String artHeadPortrait;
    @ApiModelProperty("艺术家名称")
    private String artName;
    @ApiModelProperty("点赞数")
    private Long likesNum;
    @ApiModelProperty("id")
    private Long opusId;
}
