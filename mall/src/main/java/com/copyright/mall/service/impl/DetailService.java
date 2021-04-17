package com.copyright.mall.service.impl;

import com.copyright.mall.bean.*;
import com.copyright.mall.bean.enumeration.ShopTypeEnum;
import com.copyright.mall.dao.ArtistOpusMapper;
import com.copyright.mall.domain.exception.BusinessException;
import com.copyright.mall.domain.requeest.detail.DetailParam;
import com.copyright.mall.domain.vo.details.DetailVO;
import com.copyright.mall.enums.DetailType;
import com.copyright.mall.service.IDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DetailService
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/28 1:25 下午
 */
@Service
public class DetailService implements IDetailService {

  @Resource
  private ItemService itemService;

  @Resource
  private ArtistOpusMapper artistOpusMapper;

  @Resource
  private MallService mallService;

  @Resource
  private ShopService shopService;

  @Resource
  private CopyrightService copyrightService;

  @Override
  public DetailVO getDetail(DetailParam detailParam) {
    if (DetailType.artist.name().equals(detailParam.getType())) {
      return getArtist(detailParam);
    } else if (DetailType.mall.name().equals(detailParam.getType())) {
      return getMall(detailParam);
    } else if (DetailType.copyright.name().equals(detailParam.getType())) {
      return getCopyright(detailParam);
    } else {
      throw new BusinessException("不支持的type类型");
    }
  }

  private DetailVO getArtist(DetailParam detailParam) {
    DetailVO detailVO = new DetailVO();
    DetailVO.DetailData detailData = new DetailVO.DetailData();
    List<DetailVO.Products> products = new ArrayList<>();
    List<DetailVO.Opus> opuses = new ArrayList<>();

    Shop shop = shopService.selectByPrimaryKey(detailParam.getDataId());

    List<Item> list = itemService.selectAll();
    List<Item> artists = list.stream().filter(item -> item.getShopId().equals(detailParam.getDataId())).collect(Collectors.toList());
    if (shop == null) {
      throw new BusinessException("未查询到数据");
    }
    //Item artist = artists.get(0);
    detailData.setArtCategory(shop.getShopArtcategory());
    detailData.setArtistId(shop.getId());
    detailData.setArtistName(shop.getShopName());
    detailData.setAvatar(shop.getShopLogo());
    detailData.setArtIntroduction(shop.getCertification());
    detailData.setPosterPic(shop.getShopImg());
    detailVO.setData(detailData);

    ArtistOpus artistOpus = new ArtistOpus();
    artistOpus.setItemId(shop.getId());
    List<ArtistOpus> artistOpuses = artistOpusMapper.selectByObjectList(artistOpus);
    artistOpuses.forEach(artistOpus1 -> {
      DetailVO.Opus opus = new DetailVO.Opus();
      opus.setImage(artistOpus1.getImage());
      opus.setName(artistOpus1.getName());
      opus.setOpusId(artistOpus1.getId());
      opuses.add(opus);
    });
    artists.forEach(item -> {
      DetailVO.Products products1 = new DetailVO.Products();
      products1.setImage(item.getTitleImg());
      products1.setProductId(item.getId());
      products1.setProductName(item.getItemTitle());
      if (item.getPrice() != null) {
        BigDecimal b = new BigDecimal(item.getPrice());
        String result = b.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).toString();
        products1.setProductPrice(result);
      }
      if(item.getUnderlinedPrice() != null){
        BigDecimal b = new BigDecimal(item.getUnderlinedPrice());
        String result = b.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).toString();
        products1.setProductUnderlinedPrice(result);
      }
      if(item.getInventory() != null){
        products1.setProductInventory(item.getInventory().toString());
      }
      products1.setShopID(shop.getId());
      products1.setShopName(shop.getShopName());
      products.add(products1);
    });
    detailVO.setProducts(products);

    detailVO.setOpus(opuses);

    return detailVO;
  }

  private DetailVO getMall(DetailParam detailParam) {
    DetailVO detailVO = new DetailVO();
    DetailVO.DetailData detailData = new DetailVO.DetailData();
    List<DetailVO.Opus> opuses = new ArrayList<>();
    List<DetailVO.Products> products = new ArrayList<>();

    Shop shop = shopService.selectByPrimaryKey(detailParam.getShopId());
    detailData.setOrganizationImage(shop.getShopLogo());
    detailData.setOrganizationName(shop.getShopName());
    detailData.setIsIdentification(shop.getIsIdentification() == 1 ? "true" : "false");
    detailVO.setData(detailData);


    List<Item> list = itemService.selectAll();
    List<Item> items = list.stream().filter(item -> item.getShopId().equals(detailParam.getShopId())).collect(Collectors.toList());
    items.forEach(item -> {
      DetailVO.Products products1 = new DetailVO.Products();
      products1.setImage(item.getTitleImg());
      products1.setProductId(item.getId());
      products1.setProductName(item.getItemTitle());
      if (item.getPrice() != null) {
        BigDecimal b = new BigDecimal(item.getPrice());
        String result = b.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).toString();
        products1.setProductPrice(result);
      }
      if(item.getUnderlinedPrice() != null){
        BigDecimal b = new BigDecimal(item.getUnderlinedPrice());
        String result = b.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).toString();
        products1.setProductUnderlinedPrice(result);
      }
      if(item.getInventory() != null){
        products1.setProductInventory(item.getInventory().toString());
      }
      products1.setShopID(shop.getId());
      products1.setShopName(shop.getShopName());
      products.add(products1);
    });
    detailVO.setProducts(products);

    List<String> copyRightIds = list.stream().map(Item::getRelatedCopyright).collect(Collectors.toList());
    List<Copyright> copyrights = copyrightService.selectAllObject();
    List<Copyright> copyrights1 = copyrights.stream().filter(copyright -> copyRightIds.contains(copyright.getId().toString())).collect(Collectors.toList());
    copyrights1.forEach(copyright -> {
      DetailVO.Opus opus = new DetailVO.Opus();
      opus.setName(copyright.getName());
      opus.setImage(copyright.getCopyrightImg());
      opus.setOpusId(copyright.getId());
      opuses.add(opus);
    });
    detailVO.setOpus(opuses);

    return detailVO;
  }

  private DetailVO getCopyright(DetailParam detailParam) {
    DetailVO detailVO = new DetailVO();
    DetailVO.DetailData detailData = new DetailVO.DetailData();
    List<DetailVO.Products> products = new ArrayList<>();
    Copyright copyright = copyrightService.selectByPrimaryKey(detailParam.getDataId());
    List<Shop> shops = shopService.selectByObjectList(new Shop());
    if (copyright == null) {
      throw new BusinessException("未查询到任何数据");
    }
    detailData.setOpusIcon(copyright.getCopyrightLogo());
    detailData.setOpusName(copyright.getName());
    detailData.setConnectInfo(copyright.getChineseName());
    List<DetailVO.Images> images = new ArrayList<>();
    String[] copyImgs = copyright.getCopyrightImg().split(",");

    for (String img : copyImgs) {
      DetailVO.Images images1 = new DetailVO.Images();
      images1.setImage(img);
      images.add(images1);
    }
    detailData.setIntroduction(images);
    detailVO.setData(detailData);

    List<Item> list = itemService.selectAll();
    List<Item> items = list.stream().filter(item -> item.getRelatedCopyright().equals(detailParam.getDataId().toString())
    ).collect(Collectors.toList());
    items.forEach(item -> {
      List<Shop> shops1 = shops.stream().filter(shop1 -> item.getShopId().equals(shop1.getId())).collect(Collectors.toList());
      if(shops1.size() > 0) {
        Shop shop  = shops1.get(0);
        DetailVO.Products products1 = new DetailVO.Products();
        products1.setImage(item.getTitleImg());
        products1.setProductId(item.getId());
        products1.setProductName(item.getItemTitle());
        if (item.getPrice() != null) {
          BigDecimal b = new BigDecimal(item.getPrice());
          String result = b.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).toString();
          products1.setProductPrice(result);
        }
        if(item.getUnderlinedPrice() != null){
          BigDecimal b = new BigDecimal(item.getUnderlinedPrice());
          String result = b.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).toString();
          products1.setProductUnderlinedPrice(result);
        }
        if(item.getInventory() != null){
          products1.setProductInventory(item.getInventory().toString());
        }
        products1.setShopID(shop.getId());
        products1.setShopName(shop.getShopName());
        products.add(products1);
      }
    });
    detailVO.setProducts(products);

    return detailVO;
  }

}
