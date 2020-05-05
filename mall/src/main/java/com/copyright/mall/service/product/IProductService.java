package com.copyright.mall.service.product;

import com.copyright.mall.domain.requeest.product.AreaParam;
import com.copyright.mall.domain.requeest.product.ProductByClassparam;
import com.copyright.mall.domain.requeest.product.ProductParam;
import com.copyright.mall.domain.requeest.product.ProductSearchParam;
import com.copyright.mall.bean.resp.product.ProductSearchResp;
import com.copyright.mall.domain.dto.goods.ItemDTO;
import com.copyright.mall.domain.dto.goods.SkuDTO;
import com.copyright.mall.domain.vo.product.AreaVO;
import com.copyright.mall.domain.vo.product.ProductByClassVO;
import com.copyright.mall.domain.vo.product.ProductVO;
import com.copyright.mall.manage.domain.dto.QueryGoodsParam;
import com.copyright.mall.manage.domain.dto.UpGoodsParam;
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
  ItemDTO querySingleItemBySku(Long sku);

  AreaVO getArea(AreaParam areaParam);

  ProductByClassVO getProductByClass(ProductByClassparam productByClassparam);

  ProductVO getProduct(ProductParam productParam);

  /**
   * manage service getGoodsByGoodsParam
   * @param queryGoodsParam 查询订单列表参数
   * @return result
   */
  Wrapper<List<ProductSearchResp>> getGoods(QueryGoodsParam queryGoodsParam);

}
