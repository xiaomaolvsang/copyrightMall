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
public class CreateBlobParam {

    @ApiModelProperty("内容名称")
    private String blobTitle;

    @ApiModelProperty("内容")
    private String blob;

    @ApiModelProperty("创建人")
    private String creator;

}
