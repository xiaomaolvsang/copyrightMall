package com.copyright.mall.manage.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("内容列表")
public class BlobRes {

    @ApiModelProperty("内容ID")
    private Integer id;

    @ApiModelProperty("内容名称")
    private String blobTitle;

    @ApiModelProperty("内容")
    private String blob;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date modifyTime;

    @ApiModelProperty("创建人")
    private String creator;
}
