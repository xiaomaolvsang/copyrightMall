package com.copyright.mall.manage.controller;

import com.copyright.mall.manage.domain.dto.ModifyShopParam;
import com.copyright.mall.manage.domain.dto.QueryOrderListParam;
import com.copyright.mall.manage.domain.dto.QueryShopParam;
import com.copyright.mall.manage.domain.vo.QueryOrderListRes;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "后端商铺管理")
@Slf4j
@RestController
@RequestMapping("/manage/v1/shop")
public class ManageShopController {

    @PostMapping("/getShop")
    @ApiOperation("获取用户下的商品")
    public Wrapper<PageInfo<QueryOrderListRes>> getShop(@ApiParam @Valid @RequestBody QueryShopParam queryShopParam) {
        log.info("getShop = {}", queryShopParam);
        return WrapMapper.ok();
    }

    @PostMapping("/createShop")
    @ApiOperation("创建商铺")
    public Wrapper<PageInfo<QueryOrderListRes>> createShop(@ApiParam @Valid @RequestBody ModifyShopParam modifyShopParam) {
        log.info("createShop = {}", modifyShopParam);
        return WrapMapper.ok();
    }

    @PostMapping("/updateShop")
    @ApiOperation("修改商铺")
    public Wrapper<PageInfo<QueryOrderListRes>> updateShop(@ApiParam @Valid @RequestBody ModifyShopParam modifyShopParam) {
        log.info("updateShop = {}", modifyShopParam);
        return WrapMapper.ok();
    }

}
