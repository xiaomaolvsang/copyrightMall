package com.copyright.mall.controller.cart;

import com.alibaba.fastjson.JSON;
import com.copyright.mall.controller.BaseController;
import com.copyright.mall.domain.dto.cart.CartDTO;
import com.copyright.mall.domain.requeest.cart.EditCartParam;
import com.copyright.mall.service.ICartService;
import com.copyright.mall.util.BeanMapperUtils;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author : zhangyuchen
 * @date : 2019/11/18 15:31
 */
@Slf4j
@RestController
@RequestMapping("/v1/cart")
public class CartController extends BaseController {

    @Resource
    private ICartService cartService;

    @PutMapping("/edit")
    private Wrapper<String> edit(@RequestBody @ApiParam @Valid EditCartParam editCartParam){
        log.info("CartController.edit=>[{}]", JSON.toJSONString(editCartParam));
        CartDTO param = BeanMapperUtils.map(editCartParam,CartDTO.class);
        param.setUserId(this.getUserId());
        param.setMallId(this.getMallId());
        cartService.modifyCart(param);
        return WrapMapper.ok("修改成功");
    }
}
