package com.copyright.mall.manage.domain.dto;

import com.copyright.mall.domain.dto.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("查询作品参数")
public class OpusManageParam extends BasePage {
    @ApiModelProperty("艺术家id")
    private Long artistId;
    @ApiModelProperty("艺术家名称")
    private String artistName;
    @ApiModelProperty("作品标题")
    private String opusTitle;
}
