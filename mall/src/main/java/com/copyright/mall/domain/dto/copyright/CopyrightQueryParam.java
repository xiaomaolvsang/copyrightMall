package com.copyright.mall.domain.dto.copyright;

import com.copyright.mall.domain.dto.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class CopyrightQueryParam extends BasePage {

    @ApiModelProperty(value = "状态 10待审核 20已驳回 30已受区块链保护 40已授权")
    private int status;

    @ApiModelProperty(value = "版权名称")
    private String chineName;

    @ApiModelProperty(value = "版权人")
    private String copyRightOwner;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty
    private Integer id;

    @ApiModelProperty(value = "状态 10待审核 20已驳回 30已受区块链保护 40已授权")
    private List<Integer> statuses;

}
