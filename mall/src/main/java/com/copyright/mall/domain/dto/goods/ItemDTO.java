package com.copyright.mall.domain.dto.goods;

import com.copyright.mall.bean.Item;
import com.copyright.mall.bean.Sku;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : zhangyuchen
 * @date : 2019/11/21 19:54
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ItemDTO extends Item {

    /**
     * 查询SKU
     */
    private Sku sku;
}
