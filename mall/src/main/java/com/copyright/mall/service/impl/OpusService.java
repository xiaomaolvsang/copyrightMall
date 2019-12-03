package com.copyright.mall.service.impl;

import com.copyright.mall.bean.ArtistOpus;
import com.copyright.mall.bean.Item;
import com.copyright.mall.bean.Shop;
import com.copyright.mall.dao.ArtistOpusMapper;
import com.copyright.mall.domain.exception.BusinessException;
import com.copyright.mall.domain.requeest.opus.OpusParam;
import com.copyright.mall.domain.vo.opus.OpusVO;
import com.copyright.mall.service.IOpusService;
import com.copyright.mall.service.IShopService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * OpusService
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/28 5:23 下午
 */
@Service
public class OpusService implements IOpusService {

  @Resource
  private ArtistOpusMapper artistOpusMapper;

  @Resource
  private IShopService shopService;

  @Override
  public OpusVO getOpus(OpusParam opusParam) {
    OpusVO opusVO = new OpusVO();
    OpusVO.DataBean dataBean = new OpusVO.DataBean();

    ArtistOpus artistOpus = artistOpusMapper.selectByPrimaryKey(opusParam.getOpusId());
    if(artistOpus == null){
      throw new BusinessException("未找到任何数据");
    }
    Shop shop = shopService.selectByPrimaryKey(artistOpus.getItemId());

    List<OpusVO.DataBean.ProductImageBean> list = new ArrayList<>();

    dataBean.setArtistAvatar(shop.getShopLogo());
    dataBean.setArtistName(shop.getShopName());
    dataBean.setProductDesc(shop.getCertification());
    dataBean.setProductTitle(shop.getShopName());
    dataBean.setPublishTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(artistOpus.getGmtModified()));


    String imgs = artistOpus.getImgs();
    if(StringUtils.isNotBlank(imgs)){

      String[] imgArray = imgs.split(",");
      for(String img:imgArray){
        OpusVO.DataBean.ProductImageBean productImageBean = new OpusVO.DataBean.ProductImageBean();
        productImageBean.setImage(img);
        list.add(productImageBean);
      }
    }
    dataBean.setProductImage(list);
    opusVO.setData(dataBean);

    return opusVO;
  }
}
