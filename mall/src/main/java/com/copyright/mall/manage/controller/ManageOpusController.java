package com.copyright.mall.manage.controller;

import com.copyright.mall.domain.requeest.opus.OpusParam;
import com.copyright.mall.manage.domain.dto.OpusDelParam;
import com.copyright.mall.manage.domain.dto.OpusManageParam;
import com.copyright.mall.manage.domain.dto.OpusUpdateParam;
import com.copyright.mall.manage.domain.dto.QueryShopParam;
import com.copyright.mall.manage.domain.vo.OpusManageResp;
import com.copyright.mall.service.IOpusService;
import com.copyright.mall.util.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api(tags = "后端作品相关")
@Slf4j
@RestController
@RequestMapping("/manage/v1/opus")
public class ManageOpusController extends BaseManageController {

    @Resource
    private IOpusService opusService;

    @PostMapping("/getOpus")
    @ApiOperation("获取作品")
    public Wrapper<PageInfo<OpusManageResp>> getOpus(@ApiParam @Valid @RequestBody OpusManageParam opusManageParam) {
        return opusService.selectManageByObjectList(opusManageParam);
    }

    @PostMapping("/updateOpus")
    @ApiOperation("作品修改")
    public Wrapper<Boolean> updateOpus(@ApiParam @Valid @RequestBody OpusUpdateParam opusUpdateParam) {
        return opusService.manageUpdate(opusUpdateParam);
    }

    @PostMapping("/DelOpus")
    @ApiOperation("作品删除")
    public Wrapper<Boolean> delOpus(@ApiParam @Valid @RequestBody OpusDelParam opusDelParam) {
        return opusService.delOpus(opusDelParam);
    }
}
