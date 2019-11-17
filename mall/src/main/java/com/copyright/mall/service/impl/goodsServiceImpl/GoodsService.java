package com.copyright.mall.service.impl.goodsServiceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copyright.mall.bean.Item;
import com.copyright.mall.bean.dto.GoodsDTO;
import com.copyright.mall.bean.req.GoodsReq;
import com.copyright.mall.service.goodsService.IGoodsService;
import com.copyright.mall.service.impl.ItemService;
import com.copyright.mall.util.ListUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * GoodsService
 *
 * @author lijian
 * @version 1.0
 * @date 2019/10/10 5:09 下午
 */
@Service
public class GoodsService implements IGoodsService {

  private static Logger logger = LoggerFactory.getLogger(GoodsService.class);

  @Resource
  private ItemService itemService;

  @Override
  public List<GoodsDTO> getList(GoodsReq req) {
    logger.info("开始获取商品数据:请求参数为：" + JSON.toJSONString(req));
    Item item = new Item();
    item.setShopId(req.getShopId());
    item.setItemClass(req.getItemClass());
    item.setItemTitle(req.getItemTitle());
    if (req.getPageNo() != 0 && req.getPageSize() != 0) {
      item.setStartOfPage((req.getPageNo() - 1) * req.getPageSize());
    }
    List<Item> itemList = itemService.selectObjectListPage(item);

    List<GoodsDTO> list = (List<GoodsDTO>) ListUtil.listCopy(GoodsDTO.class, itemList);

    return list;
  }
}
