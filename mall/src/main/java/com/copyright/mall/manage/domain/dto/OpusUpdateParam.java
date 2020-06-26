package com.copyright.mall.manage.domain.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel("修改作品参数")
public class OpusUpdateParam {
    @ApiModelProperty("id")
    @NotNull(message = "id不能为空")
    private Long id;
    @ApiModelProperty("作品标题")
    private String opusTitle;
    @ApiModelProperty("作品图片")
    private List<String> opusImgs;
    @ApiModelProperty("作品正文")
    private String opusContent;
}
