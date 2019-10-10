package com.copyright.mall.service.goodsService;

import com.copyright.mall.bean.dto.GoodsDTO;
import com.copyright.mall.bean.req.GoodsReq;

import java.util.List;

/**
 * GoodsService
 *
 * @author lijian
 * @version 1.0
 * @date 2019/10/10 5:05 下午
 */
public interface IGoodsService {
  List<GoodsDTO> getList(GoodsReq req);
}
