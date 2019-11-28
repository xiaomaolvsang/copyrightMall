package com.copyright.mall.domain.dto.cart;

import lombok.Data;

import java.util.List;

/**
 * @author : zhangyuchen
 * @date : 2019/11/28 10:14
 */
@Data
public class DeleteCartParam {
    private List<Long> skus;
    private Long userId;
}
