package com.copyright.mall.domain.dto.cart;

import com.copyright.mall.bean.Cart;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : zhangyuchen
 * @date : 2019/11/18 15:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CartDTO extends Cart {

    /**
     * 修改数量
     */
    private Integer modifyCount;
}
