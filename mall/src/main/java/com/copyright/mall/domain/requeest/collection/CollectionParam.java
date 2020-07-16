package com.copyright.mall.domain.requeest.collection;


import com.copyright.mall.domain.dto.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("收藏请求参数")
public class CollectionParam extends BasePage {
    @ApiModelProperty("用户id")
    private Long userId;
}
