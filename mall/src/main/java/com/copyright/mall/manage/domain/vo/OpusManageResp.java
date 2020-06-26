package com.copyright.mall.manage.domain.vo;

import com.copyright.mall.domain.dto.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("作品信息")
public class OpusManageResp extends BasePage {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("作品名称")
    private String opusName;
    @ApiModelProperty("艺术家id")
    private Long artistId;
    @ApiModelProperty("艺术家名称")
    private String artistName;
    @ApiModelProperty("创建时间")
    private String createDate;
    @ApiModelProperty("修改时间")
    private String modifyDate;
    @ApiModelProperty("作品图片")
    private List<String> opusImgs;
    @ApiModelProperty("作品详情")
    private String opusContent;


}
