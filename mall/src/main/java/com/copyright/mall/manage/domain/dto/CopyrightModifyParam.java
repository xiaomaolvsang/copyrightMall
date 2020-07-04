package com.copyright.mall.manage.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@ApiModel
public class CopyrightModifyParam {

    @ApiModelProperty(value = "id",required = true)
    @NotNull(message = "id 不能为空")
    private Integer id ;

    @ApiModelProperty(value = "作品名称")
    private String chineseName;

    @ApiModelProperty(value = "版权人")
    private String copyrightOwner;

    @ApiModelProperty(value = "类别")
    private List<String> category;

    @ApiModelProperty(value = "创作时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date creationDate;

    @ApiModelProperty(value = "版权人证件")
    private List<String> certificateOfCopyrightOwner;

    @ApiModelProperty(value = "作品图片")
    private List<String> picturesOfWorks;

    @ApiModelProperty(value = "版权证书")
    private List<String> copyrightCertificate;

    @ApiModelProperty(value = "登记号")
    private String registrationNo;
}
