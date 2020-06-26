package com.copyright.mall.manage.domain.dto;

import com.copyright.mall.bean.enumeration.ShopStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("艺术家审核")
public class PassArtistParam {
    @ApiModelProperty("审核状态：（1：通过，2：驳回）")
    private Integer status;
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("真实名称")
    private String name;
    @ApiModelProperty("昵称")
    private String petName;
    @ApiModelProperty("联系电话")
    private String phone;
    @ApiModelProperty("标签")
    private String artCategory;
    @ApiModelProperty("艺术家头像")
    private String logo;
    @ApiModelProperty("用户手机号")
    private String userPhone;
    @ApiModelProperty("密码")
    private String passwd;
}
