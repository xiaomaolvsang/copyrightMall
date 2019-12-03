package com.copyright.mall.service.impl.product;

import com.copyright.mall.bean.*;
import com.copyright.mall.bean.enumeration.AreaEnum;
import com.copyright.mall.bean.enumeration.ShopTypeEnum;
import com.copyright.mall.bean.resp.product.ProductSearchResp;
import com.copyright.mall.domain.dto.cart.CartDTO;
import com.copyright.mall.domain.dto.goods.ItemDTO;
import com.copyright.mall.domain.exception.BusinessException;
import com.copyright.mall.domain.requeest.product.AreaParam;
import com.copyright.mall.domain.requeest.product.ProductByClassparam;
import com.copyright.mall.domain.requeest.product.ProductParam;
import com.copyright.mall.domain.requeest.product.ProductSearchParam;
import com.copyright.mall.domain.vo.product.AreaVO;
import com.copyright.mall.domain.vo.product.ProductByClassVO;
import com.copyright.mall.domain.vo.product.ProductVO;
import com.copyright.mall.service.IClassificationService;
import com.copyright.mall.service.IItemService;
import com.copyright.mall.service.IShopService;
import com.copyright.mall.service.ISkuService;
import com.copyright.mall.service.impl.ClassItemRelationService;
import com.copyright.mall.service.impl.ClassificationService;
import com.copyright.mall.service.impl.CopyrightService;
import com.copyright.mall.service.impl.ShopService;
import com.copyright.mall.service.product.IProductService;
import com.copyright.mall.util.BeanMapperUtils;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.geom.Area;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
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

  @Override
  public Wrapper<List<ProductSearchResp>> search(ProductSearchParam productSearchParam) {
    Shop shop = new Shop();
    shop.setMallId(productSearchParam.getMallId());

    ShopTypeEnum shopType = ShopTypeEnum.getTypeByName(productSearchParam.getType());
    if (shopType == null) {
      return WrapMapper.error("搜索类型有误");
    }
    List<Shop> shops = shopService.selectByObjectList(shop);
    List<ProductSearchResp> productSearchResps = new ArrayList<>();
    if(ShopTypeEnum.product.getName().equals(productSearchParam.getType())) {
      List<Long> shopIds = shops.stream().filter(shop1 -> shop1.getShopType() == shopType.getCode())
        .map(Shop::getId).collect(Collectors.toList());
      List<Item> items = itemService.selectAll();
      List<Item> itemResult = items.stream().filter(item ->
        shopIds.contains(item.getShopId()) && item.getItemTitle().contains(productSearchParam.getKeyword())
      ).skip((productSearchParam.getPageNo()) * productSearchParam.getPageSize())
        .limit(productSearchParam.getPageSize())
        .collect(Collectors.toList());
      itemResult.forEach(item -> {
        List<Shop> shopTemp = shops.stream().filter(shop1 -> shop1.getId().equals(item.getShopId())).collect(Collectors.toList());
        ProductSearchResp productSearchResp = new ProductSearchResp();
        productSearchResp.setAvatar(item.getAd());
        productSearchResp.setImage(item.getTitleImg());
        productSearchResp.setType(shopType.getName());
        productSearchResp.setProductId(item.getId());
        productSearchResp.setProductName(item.getItemTitle());
        if (item.getPrice() != null) {
          BigDecimal b = new BigDecimal(item.getPrice());
          String result = b.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).toString();
          productSearchResp.setProductPrice(result);
        }
        productSearchResp.setShoID(item.getShopId());
        productSearchResp.setShopName(shopTemp.size() == 0 ? "" : shopTemp.get(0).getShopName());
        productSearchResps.add(productSearchResp);
      });

      return WrapMapper.ok(productSearchResps);
    }else {
      List<Shop> shopResults = shops.stream()
        .filter(shop1 -> shop1.getShopType() == shopType.getCode() && shop1.getShopName().contains(productSearchParam.getKeyword()))
        .skip((productSearchParam.getPageNo()) * productSearchParam.getPageSize())
        .limit(productSearchParam.getPageSize())
        .collect(Collectors.toList());

      shopResults.forEach(shop1 -> {
        ProductSearchResp productSearchResp = new ProductSearchResp();
        productSearchResp.setAvatar(shop1.getShopLogo());
        productSearchResp.setImage(shop1.getShopImg());
        productSearchResp.setType(shopType.getName());
        productSearchResp.setProductId(shop1.getId());
        productSearchResp.setProductName(shop1.getShopName());
        productSearchResp.setShoID(shop1.getId());
        productSearchResp.setShopName(shop1.getShopName());
        productSearchResp.setArtCategory(shop1.getShopArtcategory());
        productSearchResps.add(productSearchResp);
      });

      return WrapMapper.ok(productSearchResps);
    }
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
    areaVO.setType(areaParam.getType());
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

    PageHelper.startPage(productByClassparam.getPageNum(), productByClassparam.getPageSize());
    ClassItemRelation classItemRelation = new ClassItemRelation();
    classItemRelation.setClassId(productByClassparam.getFirstCategoryId());
    List<ClassItemRelation> classItemRelations = classItemRelationService.selectByObjectList(classItemRelation);
    PageInfo.of(classItemRelations);
    List<Long> itemIds = classItemRelations.stream().map(ClassItemRelation::getItemId).collect(Collectors.toList());
    List<Item> items = itemService.selectAll();

    ProductByClassVO productByClassVO = new ProductByClassVO();
    List<AreaVO.AreaAttr> attrList = new ArrayList<>();
    items.stream().filter(item -> itemIds.contains(item.getId()))
      .forEach(item -> {
        AreaVO.AreaAttr areaAttr = new AreaVO.AreaAttr();
        areaAttr.setImage(item.getTitleImg());
        areaAttr.setProductId(String.valueOf(item.getId()));
        areaAttr.setProductName(item.getItemTitle());
        if(item.getPrice() != null) {
          BigDecimal b = new BigDecimal(item.getPrice());
          String result = b.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).toString();
          areaAttr.setProductPrice(result);
        }
        areaAttr.setShopID(item.getShopId());
        List<Shop> shopTemp = shops.stream().filter(shop1 -> shop1.getId().equals(item.getShopId())).collect(Collectors.toList());
        areaAttr.setShopName(shopTemp.size() == 0 ? "" : shopTemp.get(0).getShopName());
        attrList.add(areaAttr);
      });
    productByClassVO.setData(attrList);

    return productByClassVO;
  }

  @Override
  public ProductVO getProduct(ProductParam productParam) {

    List<Item> items = itemService.selectAll();
    List<Item> items1 = items.stream().filter(item -> item.getId().equals(productParam.getProductId())).collect(Collectors.toList());
    if (items1.size() == 0) {
      throw new BusinessException("未查到数据");
    }
    Item item = items1.get(0);

    ProductVO productVO = new ProductVO();
    ProductVO.DataBean dataBean = new ProductVO.DataBean();
    dataBean.setProductId(item.getId());
    dataBean.setProductImage(item.getTitleImg());
    dataBean.setProductName(item.getItemTitle());
    if(item.getPrice() != null) {
      BigDecimal b = new BigDecimal(item.getPrice());
      String result = b.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).toString();
      dataBean.setProductPrice(result);
    }

    ProductVO.DataBean.InstitutionBean institution = new ProductVO.DataBean.InstitutionBean();
    Shop shop = shopService.selectByPrimaryKey(item.getShopId());
    institution.setInstitutionName(shop.getShopName());
    institution.setInstitutionId(shop.getId());
    institution.setInstitutionAvatar(shop.getShopLogo());
    dataBean.setInstitution(institution);

    String imgs = item.getContentImg();
    List<ProductVO.DataBean.DescImageBean> list = new ArrayList<>();
    if (StringUtils.isNotBlank(imgs)) {
      String[] img = imgs.split(",");
      for (String img1 : img) {
        ProductVO.DataBean.DescImageBean descImageBean = new ProductVO.DataBean.DescImageBean();
        descImageBean.setImage(img1);
        list.add(descImageBean);
      }
    }

    dataBean.setDescImage(list);
    List<Item> itemList = getRecommend(item.getId());
    List<ProductVO.DataBean.RecommendBean> recommend = new ArrayList<>();
    itemList.forEach(item1 -> {
      Shop shop1 = shopService.selectByPrimaryKey(item1.getShopId());
      ProductVO.DataBean.RecommendBean recommendBean = new ProductVO.DataBean.RecommendBean();
      recommendBean.setImage(item1.getTitleImg());
      recommendBean.setInstitutionName(shop1.getShopName());
      recommendBean.setProductId(item1.getId());
      recommendBean.setProductName(item1.getItemTitle());
      if(item1.getPrice() != null) {
        BigDecimal b1 = new BigDecimal(item1.getPrice());
        String result1 = b1.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).toString();
        recommendBean.setProductPrice(result1);
      }
      recommend.add(recommendBean);
    });

    dataBean.setRecommend(recommend);
    productVO.setData(dataBean);
    return productVO;
  }

  private List<Item> getRecommend(Long itemId) {
    ClassItemRelation classItemRelation = new ClassItemRelation();
    classItemRelation.setItemId(itemId);
    List<ClassItemRelation> classItemRelations = classItemRelationService.selectByObjectList(classItemRelation);
    List<Item> items = itemService.selectAll();
    if (classItemRelations.size() == 0) {
      Collections.shuffle(items);
      return items.subList(0, Math.min(items.size(),5));
    }
    ClassItemRelation classification = classItemRelations.get(0);
    ClassItemRelation classItemRelation1 = new ClassItemRelation();
    classItemRelation1.setId(classification.getClassId());
    List<ClassItemRelation> classItemRelations1 = classItemRelationService.selectByObjectList(classItemRelation);
    if (classItemRelations1.size() < 5) {
      Collections.shuffle(items);
      return items.subList(0, Math.min(items.size(),5));
    }
    List<Long> itemIds = classItemRelations1.stream().map(ClassItemRelation::getItemId).collect(Collectors.toList());

    return items.stream().filter(item -> itemIds.contains(item.getId())).limit(5).collect(Collectors.toList());
  }

  private void getProductArea(AreaParam areaParam, AreaVO areaVO) {
    Shop shop = new Shop();
    shop.setMallId(areaParam.getMallId());
    List<Shop> shops = shopService.selectByObjectList(shop);
    List<Long> shopIds = shops.stream().map(Shop::getId).collect(Collectors.toList());
    List<Item> items = itemService.selectAll();
    List<Item> itemResult = items.stream().filter(item ->
      shopIds.contains(item.getShopId())
    ).skip((areaParam.getPageNum()) * areaParam.getPageSize())
      .limit(areaParam.getPageSize())
      .collect(Collectors.toList());
    List<AreaVO.AreaAttr> areaAttrs = new ArrayList<>();
    itemResult.forEach(item -> {
      List<Shop> shopTemp = shops.stream().filter(shop1 -> shop1.getId().equals(item.getShopId())).collect(Collectors.toList());
      AreaVO.AreaAttr areaAttr = new AreaVO.AreaAttr();
      areaAttr.setImage(item.getTitleImg());
      areaAttr.setProductId(String.valueOf(item.getId()));
      areaAttr.setProductName(item.getItemTitle());
      if(item.getPrice() != null) {
        BigDecimal b = new BigDecimal(item.getPrice());
        String result = b.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).toString();
        areaAttr.setProductPrice(result);
      }
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
    List<Copyright> copyrights = copyrightService.selectAllObject();
    List<AreaVO.AreaAttr> areaAttrs = new ArrayList<>();
    List<Copyright> copyrights1 = copyrights.stream()
      .filter(copyright -> shopIds.contains(copyright.getShopId()))
      .skip((areaParam.getPageNum()) * areaParam.getPageSize())
      .limit(areaParam.getPageSize())
      .collect(Collectors.toList());
    copyrights1.forEach(copyright -> {
      AreaVO.AreaAttr areaAttr = new AreaVO.AreaAttr();
      areaAttr.setImage(copyright.getCopyrightLogo());
      areaAttr.setProductId(String.valueOf(copyright.getId()));
      areaAttr.setProductName(copyright.getName());
      areaAttrs.add(areaAttr);
    });
    areaVO.setAreaAttrs(areaAttrs);
  }


}
