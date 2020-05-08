package com.copyright.mall.manage.controller;

import com.copyright.mall.bean.Kv;
import com.copyright.mall.manage.domain.dto.CreateBlobParam;
import com.copyright.mall.manage.domain.dto.ModifyBlobParam;
import com.copyright.mall.manage.domain.dto.ModifyPriceParam;
import com.copyright.mall.manage.domain.dto.QueryBlobListParam;
import com.copyright.mall.manage.domain.vo.BlobRes;
import com.copyright.mall.service.IKvService;
import com.copyright.mall.util.BeanMapperUtils;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.github.pagehelper.PageHelper;
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

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Api(tags = "后端内容管理")
@Slf4j
@RestController
@RequestMapping("/manage/v1/blob")
public class ManageBlobController extends BaseManageController{


    @Resource
    private IKvService kvService;

    @GetMapping("/list")
    @ApiOperation("内容列表")
    private Wrapper<PageInfo<BlobRes>> list(@ApiParam @Valid  QueryBlobListParam queryBlobListParam){
        log.info("queryBlobListParam = {}",queryBlobListParam);
        PageHelper.startPage(queryBlobListParam.getPageNum(),queryBlobListParam.getPageSize());
        PageInfo<Kv> kvPageInfo = PageInfo.of(kvService.queryKvList(queryBlobListParam));
        List<BlobRes> blobResList = Lists.newArrayList();
        for(Kv kv : kvPageInfo.getList()){
            BlobRes blobRes = new BlobRes();
            blobRes.setId(kv.getId());
            blobRes.setBlobTitle(kv.getBlobTitle());
            blobRes.setBlob(kv.getContent());
            blobRes.setCreateTime(blobRes.getCreateTime());
            blobRes.setModifyTime(blobRes.getModifyTime());
            blobRes.setCreator(blobRes.getCreator());
            blobResList.add(blobRes);
        }
        PageInfo<BlobRes> response = BeanMapperUtils.map(kvPageInfo,PageInfo.class);
        response.setList(blobResList);
        return WrapMapper.ok(response);
    }

    @PostMapping("/create")
    @ApiOperation("创建内容")
    private Wrapper<Boolean> create(@ApiParam @Valid @RequestBody CreateBlobParam createBlobParam){
        log.info("createBlobParam = {}",createBlobParam);
        Kv kv = new Kv();
        kv.setBlobTitle(createBlobParam.getBlobTitle());
        kv.setContent(createBlobParam.getBlob());
        if(this.getUserId() != null) {
            kv.setCreator(this.getUserId().toString());
        }
        kvService.insertSelective(kv);
        return WrapMapper.ok();
    }

    @PostMapping("/modify")
    @ApiOperation("修改内容")
    private Wrapper<Boolean> modify(@ApiParam @Valid @RequestBody ModifyBlobParam modifyBlobParam){
        log.info("modifyPriceParam = {}",modifyBlobParam);
        Kv kv = new Kv();
        kv.setId(modifyBlobParam.getId());
        kv.setBlobTitle(modifyBlobParam.getBlobTitle());
        kv.setContent(modifyBlobParam.getBlob());
        if(this.getUserId() != null) {
            kv.setCreator(this.getUserId().toString());
        }
        kvService.updateByPrimaryKeySelective(kv);
        return WrapMapper.ok();
    }
}
