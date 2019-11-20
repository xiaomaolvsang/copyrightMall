package com.copyright.mall.controller.productController;

import com.copyright.mall.aspect.ControllerErro;
import com.copyright.mall.domain.requeest.product.ProductSearchParam;
import com.copyright.mall.bean.resp.product.ProductSearchResp;
import com.copyright.mall.controller.BaseController;
import com.copyright.mall.service.product.IProductService;
import com.copyright.mall.util.wrapper.Wrapper;
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
import java.util.List;

/**
 * productController
 *
 * @author lijian
 * @version 1.0
 * @date 2019/11/17 2:23 下午
 */
@RestController
@Api(tags = {"商品相关"})
@RequestMapping("/product")
@Slf4j
public class ProductController extends BaseController {

  @Resource
  private IProductService productService;

  @ApiOperation(value = "商品搜索")
  @PostMapping("/search")
  @ControllerErro
  public Wrapper<List<ProductSearchResp>> test(@RequestBody @ApiParam @Valid ProductSearchParam productSearchParam){
    return productService.search(productSearchParam);
  }
}
