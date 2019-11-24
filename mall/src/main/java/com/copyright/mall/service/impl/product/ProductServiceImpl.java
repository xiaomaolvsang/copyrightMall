package com.copyright.mall.service.impl.product;

import com.copyright.mall.bean.Item;
import com.copyright.mall.bean.Shop;
import com.copyright.mall.bean.Sku;
import com.copyright.mall.bean.enumeration.ShopTypeEnum;
import com.copyright.mall.bean.resp.product.ProductSearchResp;
import com.copyright.mall.domain.dto.goods.ItemDTO;
import com.copyright.mall.domain.requeest.product.ProductSearchParam;
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
    Shop shop = new Shop();
    shop.setMallId(productSearchParam.getMallId());

    ShopTypeEnum shopType = ShopTypeEnum.getTypeByName(productSearchParam.getType());
    if(shopType == null){
      return WrapMapper.error("搜索类型有误");
    }
    List<Shop> shops = shopService.selectByObjectList(shop);
    List<Long> shopIds = shops.stream().filter(shop1 -> shop1.getShopType() == shopType.getCode())
      .map(Shop::getId).collect(Collectors.toList());
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
      productSearchResp.setType(shopType.getName());
      productSearchResp.setProductId(item.getId());
      productSearchResp.setProductName(item.getItemTitle());
      productSearchResp.setProductPrice(item.getPrice());
      productSearchResp.setShoID(item.getShopId());
      productSearchResp.setShopName(shopTemp.size() == 0 ? "" : shopTemp.get(0).getShopName());
      productSearchResps.add(productSearchResp);
    });

    return WrapMapper.ok(productSearchResps);
  }

  @Override
  public ItemDTO querySingleItemBySku(Long skuId) {
    Sku sku =  skuService.selectByPrimaryKey(skuId);
    if(skuId==null){
      return null;
    }
    Item item = itemService.selectByPrimaryKey(sku.getItemId());
    if(item==null){
      return null;
    }
    ItemDTO itemDTO = BeanMapperUtils.map(item,ItemDTO.class);
    itemDTO.setSku(sku);
    return itemDTO;
  }
}
