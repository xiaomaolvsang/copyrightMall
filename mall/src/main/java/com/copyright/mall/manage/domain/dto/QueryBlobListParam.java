package com.copyright.mall.manage.domain.dto;

import com.copyright.mall.domain.dto.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("查询内容列表参数")
public class QueryBlobListParam extends BasePage {

    @ApiModelProperty("内容ID")
    private String id;

    @ApiModelProperty("内容名称")
    private String blobTitle;

    @ApiModelProperty("起始时间")
    private Date starTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("内容状态 0-正常 1-已删除")
    private int blobType;
}
