package com.copyright.mall.service.impl;

import com.copyright.mall.bean.ArtistOpus;
import com.copyright.mall.bean.Item;
import com.copyright.mall.dao.ArtistOpusMapper;
import com.copyright.mall.domain.exception.BusinessException;
import com.copyright.mall.domain.requeest.opus.OpusParam;
import com.copyright.mall.domain.vo.opus.OpusVO;
import com.copyright.mall.service.IOpusService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
  private ItemService itemService;

  @Override
  public OpusVO getOpus(OpusParam opusParam) {
    OpusVO opusVO = new OpusVO();
    OpusVO.DataBean dataBean = new OpusVO.DataBean();
    List<Item> items = itemService.selectAll();

    ArtistOpus artistOpus = artistOpusMapper.selectByPrimaryKey(opusParam.getOpusId());
    List<Item> results = items.stream().filter(item -> item.getId().equals(artistOpus.getItemId())).collect(Collectors.toList());
    if(results.size() == 0){
      throw new BusinessException("未找到对应的艺术家");
    }
    Item aretist = results.get(0);
    List<OpusVO.DataBean.ProductImageBean> list = new ArrayList<>();

    dataBean.setArtistAvatar(aretist.getAd());
    dataBean.setArtistName(aretist.getItemTitle());
    dataBean.setProductDesc(artistOpus.getOpusDesc());
    dataBean.setProductTitle(artistOpus.getName());
    dataBean.setPublishTime(artistOpus.getGmtModified().toString());


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
