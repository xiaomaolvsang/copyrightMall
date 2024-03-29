package com.copyright.mall.manage.controller;

import com.copyright.mall.manage.domain.dto.QueryGoodsParam;
import com.copyright.mall.manage.domain.dto.UpDownGoodsParam;
import com.copyright.mall.manage.domain.dto.UpGoodsParam;
import com.copyright.mall.manage.domain.vo.GetGoodsResp;
import com.copyright.mall.service.product.IProductService;
import com.copyright.mall.util.wrapper.WrapMapper;
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

@Api(tags = "后端商品管理")
@Slf4j
@RestController
@RequestMapping("/manage/v1/goods")
public class ManageGoodsController {

    @Resource
    private IProductService iProductService;

    @PostMapping("/getGoods")
    @ApiOperation("获取商品信息")
    public Wrapper<PageInfo<GetGoodsResp>> getGoods(@ApiParam @Valid @RequestBody QueryGoodsParam queryGoodsParam) {
        log.info("getShop = {}", queryGoodsParam);
        return iProductService.getGoods(queryGoodsParam);
    }

    @PostMapping("/offGoods")
    @ApiOperation("下架/上架商品")
    public Wrapper<Boolean> offGoods(@ApiParam @Valid @RequestBody UpDownGoodsParam upDownGoodsParam) {
        log.info("getShop = {}", upDownGoodsParam);
        return iProductService.downGoods(upDownGoodsParam);
    }

    @PostMapping("/upGoods")
    @ApiOperation("商品新增")
    public Wrapper<Boolean> upGoods(@ApiParam @Valid @RequestBody UpGoodsParam upGoodsParam) {
        log.info("getShop = {}", upGoodsParam);
        return iProductService.upGoods(upGoodsParam);
    }

    @PostMapping("/updateGoods")
    @ApiOperation("商品修改")
    public Wrapper<Boolean> updateGoods(@ApiParam @Valid @RequestBody UpGoodsParam upGoodsParam) {
        log.info("getShop = {}", upGoodsParam);
        return iProductService.upGoods(upGoodsParam);
    }
}
