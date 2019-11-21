package com.copyright.mall.controller.cart;

import com.alibaba.fastjson.JSON;
import com.copyright.mall.bean.Shop;
import com.copyright.mall.controller.BaseController;
import com.copyright.mall.domain.dto.cart.CartDTO;
import com.copyright.mall.domain.dto.goods.ItemDTO;
import com.copyright.mall.domain.requeest.cart.CharListQueryParam;
import com.copyright.mall.domain.requeest.cart.EditCartParam;
import com.copyright.mall.domain.vo.CartListVO;
import com.copyright.mall.service.ICartService;
import com.copyright.mall.service.impl.ShopService;
import com.copyright.mall.service.product.IProductService;
import com.copyright.mall.util.BeanMapperUtils;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Resource
    private ShopService shopService;

    @Resource
    private IProductService productService;

    @PutMapping("/edit")
    private Wrapper<String> edit(@RequestBody @ApiParam @Valid EditCartParam editCartParam){
        log.info("CartController.edit=>[{}]", JSON.toJSONString(editCartParam));
        CartDTO param = BeanMapperUtils.map(editCartParam,CartDTO.class);
        param.setUserId(this.getUserId());
        param.setMallId(this.getMallId());
        cartService.modifyCart(param);
        return WrapMapper.ok("修改成功");
    }

    @GetMapping("/list")
    private Wrapper<CartListVO> cartList (@ApiParam CharListQueryParam charListQueryParam){
        CartDTO queryParam = new CartDTO();
        queryParam.setUserId(this.getUserId());
        queryParam.setMallId(this.getMallId());
        queryParam.setShopId(charListQueryParam.getShopId());
        Page<CartDTO> page =  PageHelper.startPage(charListQueryParam.getPageNum(),charListQueryParam.getPageSize());
        List<CartDTO> cartDTOS = cartService.selectCartListOrderByShop(queryParam);
        PageInfo.of(cartDTOS);
        CartListVO result = new CartListVO();
        result.setPageNum(page.getPageNum());
        result.setTotal(page.getTotal());
        Map<Long,List<CartDTO>> dtos = cartDTOS.stream().collect(Collectors.groupingBy(CartDTO::getShopId,Collectors.toList()));
        List<CartListVO.ShopListBean> shopListBeans = Lists.newArrayList();
        for(Map.Entry<Long,List<CartDTO>> entry : dtos.entrySet()){
            CartListVO.ShopListBean beanItem = new CartListVO.ShopListBean();
            Shop shop = shopService.selectByPrimaryKey(entry.getKey());
            beanItem.setShopName(shop.getShopName());
            beanItem.setShopId(shop.getId());
            List<CartListVO.RelateProductsBean> beans = Lists.newArrayList();
            for(CartDTO cartDTO : entry.getValue()){
                CartListVO.RelateProductsBean productsBean = new CartListVO.RelateProductsBean();
                ItemDTO itemDTO = productService.querySingleItemBySku(cartDTO.getSkuId());
                productsBean.setImage(itemDTO.getTitleImg());
                productsBean.setInstitutionName(shop.getShopName());
                productsBean.setProductName(itemDTO.getItemTitle());
                productsBean.setProductPrice(BigDecimal.valueOf(itemDTO.getSku().getPrice()).divide(new BigDecimal(100)));
                productsBean.setProductId(itemDTO.getSku().getId());
                productsBean.setNum(cartDTO.getCount());
                beans.add(productsBean);
            }
            beanItem.setRelateProducts(beans);
            shopListBeans.add(beanItem);
        }
        result.setShopList(shopListBeans);
        return WrapMapper.ok(result);
    }


}
