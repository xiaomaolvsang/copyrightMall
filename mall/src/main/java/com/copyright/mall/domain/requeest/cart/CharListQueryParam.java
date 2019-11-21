package com.copyright.mall.domain.requeest.cart;

import com.copyright.mall.domain.dto.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : zhangyuchen
 * @date : 2019/11/21 19:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class CharListQueryParam extends BasePage {

    /**
     * 店铺ID
     */
    @ApiModelProperty("店铺编码")
    private Long shopId;

}
