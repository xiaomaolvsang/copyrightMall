package com.copyright.mall.domain.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * @author : zhangyuchen
 * @date : 2019/12/1 11:44
 */
@Data
@ApiModel
public class QueryEncryptedInfoParam {

    /**
     * 加密数据
     */
    @ApiModelProperty("加密数据")
    private String encryptedData;

    /**
     * 加密数据
     */
    @ApiModelProperty("偏移")
    private String iv;
}
