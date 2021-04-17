package com.copyright.mall.manage.domain.vo;

import com.copyright.mall.domain.dto.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("banner参数")
public class GetBannerResp  extends BasePage {

    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("banner类型")
    private String bannerType;
    @ApiModelProperty("图片")
    private String image;
    @ApiModelProperty("链接类型")
    private String linkType;
    @ApiModelProperty("链接")
    private String targetUrl;
    @ApiModelProperty("名称")
    private String categoryName;

}
