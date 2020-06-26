package com.copyright.mall.service;

import com.copyright.mall.domain.requeest.opus.CreateOpusReq;
import com.copyright.mall.domain.requeest.opus.DeleteOpusParam;
import com.copyright.mall.domain.requeest.opus.OpusParam;
import com.copyright.mall.domain.requeest.opus.OpusReq;
import com.copyright.mall.domain.vo.opus.OpusResp;
import com.copyright.mall.domain.vo.opus.OpusVO;
import com.copyright.mall.manage.domain.dto.OpusManageParam;
import com.copyright.mall.manage.domain.dto.OpusUpdateParam;
import com.copyright.mall.manage.domain.vo.OpusManageResp;
import com.copyright.mall.util.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;

/**
 * IOpusService
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/28 5:21 下午
 */
public interface IOpusService {
  OpusVO getOpus(OpusParam opusParam);

  PageInfo<OpusResp> selectByObjectListDesc(OpusReq opusReq);

  Wrapper<Boolean> createOpus(CreateOpusReq createOpusReq);

  Wrapper<Boolean> delete(DeleteOpusParam deleteOpusParam);

  Wrapper<PageInfo<OpusResp>> selectByObjectListOfMyDesc(OpusReq opusReq);

  Wrapper<PageInfo<OpusManageResp>> selectManageByObjectList(OpusManageParam opusManageParam);

  public Wrapper<Boolean> manageUpdate(OpusUpdateParam opusUpdateParam);
}
