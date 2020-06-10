package com.copyright.mall.manage.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Api("授权参数")
@Data
public class AuthorizationParam {

    @ApiModelProperty("主证书ID")
    @NotBlank(message = "主证书ID不能为空")
    private String pCertificateId;

    @ApiModelProperty("电话")
    @NotBlank(message = "电话不能为空")
    private String phone;

    @ApiModelProperty("授权人姓名")
    @NotBlank(message = "授权人姓名不能为空")
    private String name;

    @ApiModelProperty("授权类型")
    @NotEmpty(message = "授权类型不能为空")
    private List<String> type;

    @ApiModelProperty("截止时间")
    @NotNull(message = "截止时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date closingDate;
}
