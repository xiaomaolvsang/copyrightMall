package com.copyright.mall.domain.requeest.opus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel("作品发布或修改")
public class CreateOpusReq {
    @ApiModelProperty("图片")
    private List<String> imgs;
    @ApiModelProperty("标题")
    @NotBlank(message = "标题不能为空")
    private String title;
    @ApiModelProperty("正文")
    @NotBlank(message = "正文不能为空")
    private String opusDesc;
    @ApiModelProperty("我自己拿")
    private Long userId;
    @ApiModelProperty("修改时要")
    private Long id;
}
