package com.copyright.mall.service;

import com.copyright.mall.domain.requeest.opus.OpusParam;
import com.copyright.mall.domain.vo.opus.OpusVO;

import java.util.List;

/**
 * IOpusService
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/28 5:21 下午
 */
public interface IOpusService {
  OpusVO getOpus(OpusParam opusParam);
}
