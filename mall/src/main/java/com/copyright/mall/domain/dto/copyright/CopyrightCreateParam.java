package com.copyright.mall.domain.dto.copyright;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@ApiModel
public class CopyrightCreateParam {

    @ApiModelProperty(value = "作品名称",required = true)
    @NotBlank(message = "作品名称不能为空")
    private String chineseName;

    @ApiModelProperty(value = "版权人",required = true)
    @NotBlank(message = "版权人不能为空")
    private String copyrightOwner;

    @ApiModelProperty(value = "类别",required = true)
    @NotEmpty(message = "类别不能为空")
    private List<String> category;

    @ApiModelProperty(value = "创作时间",required = true)
    @NotNull(message = "创作时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date creationDate;

    @ApiModelProperty(value = "版权人证件",required = true)
    @NotEmpty(message = "证件不能为空")
    private List<String> certificateOfCopyrightOwner;

    @ApiModelProperty(value = "作品图片",required = true)
    @NotEmpty(message = "作品图片不能为空")
    private List<String> picturesOfWorks;

    @ApiModelProperty(value = "版权证书",required = true)
    @NotEmpty(message = "版权证书不能为空")
    private List<String> copyrightCertificate;

    @ApiModelProperty(value = "登记号",required = true)
    @NotBlank(message = "登记号")
    private String registrationNo;
}
