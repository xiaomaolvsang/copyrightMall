package com.copyright.mall.service.impl.product;

import com.copyright.mall.bean.Item;
import com.copyright.mall.bean.Shop;
import com.copyright.mall.bean.dto.GoodsDTO;
import com.copyright.mall.bean.dto.product.ProductSearchParam;
import com.copyright.mall.bean.resp.product.ProductSearchResp;
import com.copyright.mall.domain.dto.goods.SkuDTO;
import com.copyright.mall.service.IItemService;
import com.copyright.mall.service.IShopService;
import com.copyright.mall.service.ISkuService;
import com.copyright.mall.service.product.IProductService;
import com.copyright.mall.util.BeanMapperUtils;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ProductServiceImpl
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/17 2:47 下午
 */
@Service
public class ProductServiceImpl implements IProductService {

  @Resource
  private IShopService shopService;

  @Resource
  private IItemService itemService;

  @Resource
  private ISkuService skuService;

  @Override
  public Wrapper<List<ProductSearchResp>> search(ProductSearchParam productSearchParam) {

    if(!ProductSearchParam.paramChecking(productSearchParam)){
      return WrapMapper.error("");
    }
    Shop shop = new Shop();
    shop.setMallId(productSearchParam.getMallId());
    List<Shop> shops = shopService.selectByObjectList(shop);
    List<Long> shopIds = shops.stream().map(Shop::getId).collect(Collectors.toList());
    List<Item> items = itemService.selectAll();
    List<Item> itemResult = items.stream().filter(item ->
      shopIds.contains(item.getShopId()) && item.getItemTitle().contains(productSearchParam.getKeyword())
    ).skip((productSearchParam.getPageNo() - 1) * productSearchParam.getPageSize())
      .limit(productSearchParam.getPageSize())
      .collect(Collectors.toList());

    List<ProductSearchResp> productSearchResps = new ArrayList<>();
    itemResult.forEach(item -> {
      List<Shop> shopTemp = shops.stream().filter(shop1 -> shop1.getId().equals(item.getShopId())).collect(Collectors.toList());

      ProductSearchResp productSearchResp = new ProductSearchResp();
      productSearchResp.setAvatar(item.getTitleImg());
      productSearchResp.setImage(item.getContentImg());
      productSearchResp.setType(item.getItemClass());
      productSearchResp.setProductId(item.getId());
      productSearchResp.setProductName(item.getItemTitle());
      productSearchResp.setProductPrice(item.getPrice());
      productSearchResp.setShoID(item.getShopId());
      productSearchResp.setShopName(shopTemp == null ? "" : shopTemp.get(0).getShopName());
    });

    return WrapMapper.ok(productSearchResps);
  }

  @Override
  public SkuDTO querySingleItemBySku(Long sku) {
    skuService.selectByPrimaryKey(sku);
    if(sku==null){
      return null;
    }
    return BeanMapperUtils.map(sku,SkuDTO.class);
  }
}
