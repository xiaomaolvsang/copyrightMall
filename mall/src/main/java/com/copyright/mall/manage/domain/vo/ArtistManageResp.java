package com.copyright.mall.manage.domain.vo;

import com.copyright.mall.domain.dto.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("艺术家审核参数")
public class ArtistManageResp extends BasePage {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("真实名称")
    private String name;
    @ApiModelProperty("昵称")
    private String petName;
    @ApiModelProperty("联系电话")
    private String phone;
    @ApiModelProperty("提交时间")
    private String createTime;
    @ApiModelProperty("标签")
    private String artCategory;
    @ApiModelProperty("艺术家头像")
    private String logo;
    @ApiModelProperty("用户手机号")
    private String userPhone;
    @ApiModelProperty("用户id")
    private Long userId;
}
