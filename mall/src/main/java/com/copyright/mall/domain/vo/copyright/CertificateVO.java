package com.copyright.mall.domain.vo.copyright;

import com.copyright.mall.domain.dto.copyright.TimeLineDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel("版权")
public class CertificateVO {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("证书链码")
    private String certificateId;

    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date gmtCreate;

    @ApiModelProperty("修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date gmtModified;

    @ApiModelProperty("认证时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date certificationTime;

    @ApiModelProperty("创作日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date creationTime;

    @ApiModelProperty("中文名称")
    private String chineseName;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("审核状态")
    private Integer cerificateStatus;
    private String cerificateStatusDesc;

    @ApiModelProperty("版权logo")
    private String copyrightLogo;

    @ApiModelProperty("版权图片")
    private String copyrightImg;

    @ApiModelProperty("上传用户")
    private String uploadUser;

    @ApiModelProperty("商铺id")
    private Long shopId;

    @ApiModelProperty("版权人证件")
    private String certificateOfCopyrightOwner;

    @ApiModelProperty("登记号")
    private String registrationNo;

    @ApiModelProperty("类别")
    private String category;

    @ApiModelProperty("作品图片")
    private String picturesOfWorks;

    @ApiModelProperty("版权证书")
    private String copyrightCertificate;

    @ApiModelProperty("授权人")
    private String authorizer;

    @ApiModelProperty("授权人名")
    private String authorizerName;

    @ApiModelProperty("被授权人")
    private String authorizedPerson;

    @ApiModelProperty("被授权人名")
    private String authorizedPersionName;

    @ApiModelProperty("版权人")
    private String copyrightOwner;

    @ApiModelProperty("时间线")
    private String timeLine;

    @ApiModelProperty("时间线")
    private TimeLineDTO timeLineDTO;


    @ApiModelProperty("授权日期")
    private Date authorizationDate;

    @ApiModelProperty("截止日期")
    private Date closingDate;

    @ApiModelProperty("授权类型")
    private String authorizationType;

}
