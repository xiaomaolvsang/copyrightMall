package com.copyright.mall.service.impl.product;

import com.copyright.mall.bean.*;
import com.copyright.mall.bean.enumeration.AreaEnum;
import com.copyright.mall.bean.enumeration.ShopTypeEnum;
import com.copyright.mall.bean.resp.product.ProductSearchResp;
import com.copyright.mall.domain.dto.cart.CartDTO;
import com.copyright.mall.domain.dto.goods.ItemDTO;
import com.copyright.mall.domain.requeest.product.AreaParam;
import com.copyright.mall.domain.requeest.product.ProductByClassparam;
import com.copyright.mall.domain.requeest.product.ProductSearchParam;
import com.copyright.mall.domain.vo.product.AreaVO;
import com.copyright.mall.domain.vo.product.ProductByClassVO;
import com.copyright.mall.service.IItemService;
import com.copyright.mall.service.IShopService;
import com.copyright.mall.service.ISkuService;
import com.copyright.mall.service.impl.ClassItemRelationService;
import com.copyright.mall.service.impl.ClassificationService;
import com.copyright.mall.service.impl.CopyrightService;
import com.copyright.mall.service.product.IProductService;
import com.copyright.mall.util.BeanMapperUtils;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.geom.Area;
import java.math.BigDecimal;
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

  @Resource
  private CopyrightService copyrightService;

  @Resource
  private ClassItemRelationService classItemRelationService;
  private List<Shop> shops;

  @Override
  public Wrapper<List<ProductSearchResp>> search(ProductSearchParam productSearchParam) {
    Shop shop = new Shop();
    shop.setMallId(productSearchParam.getMallId());

    ShopTypeEnum shopType = ShopTypeEnum.getTypeByName(productSearchParam.getType());
    if (shopType == null) {
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
      BigDecimal b = new BigDecimal(item.getPrice());
      String result = b.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).toString();
      productSearchResp.setProductPrice(result);
      productSearchResp.setShoID(item.getShopId());
      productSearchResp.setShopName(shopTemp.size() == 0 ? "" : shopTemp.get(0).getShopName());
      productSearchResps.add(productSearchResp);
    });

    return WrapMapper.ok(productSearchResps);
  }

  @Override
  public ItemDTO querySingleItemBySku(Long skuId) {
    Sku sku = skuService.selectByPrimaryKey(skuId);
    if (skuId == null) {
      return null;
    }
    Item item = itemService.selectByPrimaryKey(sku.getItemId());
    if (item == null) {
      return null;
    }
    ItemDTO itemDTO = BeanMapperUtils.map(item, ItemDTO.class);
    itemDTO.setSku(sku);
    return itemDTO;
  }

  @Override
  public AreaVO getArea(AreaParam areaParam) {
    AreaVO areaVO = new AreaVO();
    areaParam.setType(areaParam.getType());
    if (AreaEnum.productArea.name().equals(areaParam.getType())) {
      getProductArea(areaParam, areaVO);
    } else {
      getCopyroght(areaParam, areaVO);
    }
    return areaVO;
  }

  @Override
  public ProductByClassVO getProductByClass(ProductByClassparam productByClassparam) {
    Shop shop = new Shop();
    shop.setMallId(0L);
    List<Shop> shops = shopService.selectByObjectList(shop);

    PageHelper.startPage(productByClassparam.getPageNum(),productByClassparam.getPageSize());
    ClassItemRelation classItemRelation = new ClassItemRelation();
    classItemRelation.setClassId(productByClassparam.getFirstCategoryId());
    List<ClassItemRelation> classItemRelations = classItemRelationService.selectByObjectList(classItemRelation);
    PageInfo.of(classItemRelations);
    List<Long> itemIds = classItemRelations.stream().map(ClassItemRelation::getItemId).collect(Collectors.toList());
    List<Item> items = itemService.selectAll();

    ProductByClassVO productByClassVO = new ProductByClassVO();
    List<AreaVO.AreaAttr> attrList = new ArrayList<>();
    items.stream().filter(item -> itemIds.contains(item.getId()) )
      .forEach(item -> {
        AreaVO.AreaAttr areaAttr = new AreaVO.AreaAttr();
        areaAttr.setImage(item.getTitleImg());
        areaAttr.setProductId(String.valueOf(item.getId()));
        areaAttr.setProductName(item.getItemTitle());
        BigDecimal b = new BigDecimal(item.getPrice());
        String result = b.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).toString();
        areaAttr.setProductPrice(result);
        areaAttr.setShopID(item.getShopId());
        List<Shop> shopTemp = shops.stream().filter(shop1 -> shop1.getId().equals(item.getShopId())).collect(Collectors.toList());
        areaAttr.setShopName(shopTemp.size() == 0 ? "" : shopTemp.get(0).getShopName());
        attrList.add(areaAttr);
      });
    productByClassVO.setData(attrList);

    return productByClassVO;
  }

  private void getProductArea(AreaParam areaParam, AreaVO areaVO) {
    Shop shop = new Shop();
    shop.setMallId(areaParam.getMallId());
    List<Shop> shops = shopService.selectByObjectList(shop);
    List<Long> shopIds = shops.stream().map(Shop::getId).collect(Collectors.toList());
    List<Item> items = itemService.selectAll();
    List<Item> itemResult = items.stream().filter(item ->
      shopIds.contains(item.getShopId())
    ).skip((areaParam.getPageNum() - 1) * areaParam.getPageSize())
      .limit(areaParam.getPageSize())
      .collect(Collectors.toList());
    List<AreaVO.AreaAttr> areaAttrs = new ArrayList<>();
    itemResult.forEach(item -> {
      List<Shop> shopTemp = shops.stream().filter(shop1 -> shop1.getId().equals(item.getShopId())).collect(Collectors.toList());
      AreaVO.AreaAttr areaAttr = new AreaVO.AreaAttr();
      areaAttr.setImage(item.getTitleImg());
      areaAttr.setProductId(String.valueOf(item.getId()));
      areaAttr.setProductName(item.getItemTitle());
      BigDecimal b = new BigDecimal(item.getPrice());
      String result = b.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).toString();
      areaAttr.setProductPrice(result);
      areaAttr.setShopID(item.getShopId());
      areaAttr.setShopName(shopTemp.size() == 0 ? "" : shopTemp.get(0).getShopName());
      areaAttrs.add(areaAttr);
    });
    areaVO.setAreaAttrs(areaAttrs);
  }

  private void getCopyroght(AreaParam areaParam, AreaVO areaVO) {
    Shop shop = new Shop();
    shop.setMallId(areaParam.getMallId());
    List<Shop> shops = shopService.selectByObjectList(shop);
    List<Long> shopIds = shops.stream().map(Shop::getId).collect(Collectors.toList());
    List<Item> items = itemService.selectAll();
    List<Item> itemResult = items.stream().filter(item ->
      shopIds.contains(item.getShopId())
    ).collect(Collectors.toList());
    List<Copyright> copyrights = copyrightService.selectAllObject();
    List<AreaVO.AreaAttr> areaAttrs = new ArrayList<>();
    itemResult.forEach(item -> {
      List<Shop> shopTemp = shops.stream().filter(shop1 -> shop1.getId().equals(item.getShopId())).collect(Collectors.toList());
      List<Copyright> copyrights1 = copyrights.stream().filter(copyright -> copyright.getId().equals(item.getRelatedCopyright())).collect(Collectors.toList());
      if (copyrights1.size() != 0) {
        AreaVO.AreaAttr areaAttr = new AreaVO.AreaAttr();
        areaAttr.setImage(copyrights1.get(0).getCopyrightImg());
        areaAttr.setProductId(String.valueOf(item.getId()));
        areaAttr.setProductName(item.getItemTitle());
        areaAttr.setShopID(item.getShopId());
        areaAttr.setShopName(shopTemp.size() == 0 ? "" : shopTemp.get(0).getShopName());
        areaAttrs.add(areaAttr);
      }
    });
    areaVO.setAreaAttrs(areaAttrs);
  }


}
