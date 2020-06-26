package com.copyright.mall.manage.domain.dto;

import com.copyright.mall.domain.dto.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("艺术参数")
public class ArtistManageParam extends BasePage {
    @ApiModelProperty("艺术家名称")
    private String artistName;
    @ApiModelProperty("预留电话")
    private String phone;
}
