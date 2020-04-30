package com.copyright.mall.manage.domain.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author zhangyuchen
 */
@Api("创建内容参数")
@Data
public class ModifyBlobParam {

    @ApiModelProperty(value = "内容ID",required = true)
    @NotNull(message = "内容ID不能为空")
    private Integer id;

    @ApiModelProperty("内容名称")
    private String blobTitle;

    @ApiModelProperty("内容")Z
    private String blob;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date modifyTime;

    @ApiModelProperty("创建人")
    private String creator;
}
