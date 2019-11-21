package com.copyright.mall.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author : zhangyuchen
 * @date : 2019/9/24 17:13
 */
@Data
@ApiModel
public class BasePage {
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", required = true)
    @NotNull(message = "页码不能为空")
    private Integer pageNum;

    public Integer getPageNum() {
        if (pageNum == null || pageNum < 1) {
            return 1;
        }
        return pageNum;
    }

    /**
     * 每页条数
     */
    @ApiModelProperty(value = "每页条数", required = true)
    @NotNull(message = "页大小不能为空")
    private int pageSize;
}
