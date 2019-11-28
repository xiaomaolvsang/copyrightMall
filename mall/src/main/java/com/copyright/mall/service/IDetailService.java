package com.copyright.mall.service;

import com.copyright.mall.domain.requeest.detail.DetailParam;
import com.copyright.mall.domain.vo.details.DetailVO;

/**
 * IDetailService
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/28 1:25 下午
 */
public interface IDetailService {
   DetailVO getDetail(DetailParam detailParam);
}
