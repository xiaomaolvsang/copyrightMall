package com.copyright.mall.manage.controller;

import com.copyright.mall.manage.domain.dto.CreateBlobParam;
import com.copyright.mall.manage.domain.dto.ModifyBlobParam;
import com.copyright.mall.manage.domain.dto.ModifyPriceParam;
import com.copyright.mall.manage.domain.dto.QueryBlobListParam;
import com.copyright.mall.manage.domain.vo.BlobRes;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "后端内容管理")
@Slf4j
@RestController
@RequestMapping("/manage/v1/blob")
public class ManageBlobController {

    @GetMapping("/list")
    @ApiOperation("内容列表")
    private Wrapper<PageInfo<BlobRes>> list(@ApiParam @Valid @RequestBody QueryBlobListParam queryBlobListParam){
        log.info("queryBlobListParam = {}",queryBlobListParam);

        return WrapMapper.ok();
    }

    @PostMapping("/create")
    @ApiOperation("创建内容")
    private Wrapper<Boolean> create(@ApiParam @Valid @RequestBody CreateBlobParam createBlobParam){
        log.info("createBlobParam = {}",createBlobParam);
        return WrapMapper.ok();
    }

    @PostMapping("/modify")
    @ApiOperation("修改内容")
    private Wrapper<Boolean> modify(@ApiParam @Valid @RequestBody ModifyBlobParam modifyBlobParam){
        log.info("modifyPriceParam = {}",modifyBlobParam);
        return WrapMapper.ok();
    }
}
