package com.copyright.mall.manage.domain.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.copyright.mall.domain.dto.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

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
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty("结束时间")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @ApiModelProperty("内容状态 0-正常 1-已删除")
    private int blobType;
}
