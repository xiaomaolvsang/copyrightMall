package com.copyright.mall.service.product;

import com.copyright.mall.bean.dto.product.ProductSearchParam;
import com.copyright.mall.bean.resp.product.ProductSearchResp;
import com.copyright.mall.domain.dto.goods.SkuDTO;
import com.copyright.mall.util.wrapper.Wrapper;

import java.util.List;

/**
 * IProductService
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/17 2:44 下午
 */
public interface IProductService {
  Wrapper<List<ProductSearchResp>> search(ProductSearchParam productSearchParam);

  /**
   * query single item by sku
   * @param sku query sku
   * @return the item
   */
  SkuDTO querySingleItemBySku(Long sku);
}
