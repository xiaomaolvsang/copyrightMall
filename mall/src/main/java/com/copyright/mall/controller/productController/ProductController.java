package com.copyright.mall.controller.productController;

import com.copyright.mall.aspect.ControllerErro;
import com.copyright.mall.domain.requeest.product.AreaParam;
import com.copyright.mall.domain.requeest.product.ProductByClassparam;
import com.copyright.mall.domain.requeest.product.ProductSearchParam;
import com.copyright.mall.bean.resp.product.ProductSearchResp;
import com.copyright.mall.controller.BaseController;
import com.copyright.mall.domain.vo.product.AreaVO;
import com.copyright.mall.domain.vo.product.ProductByClassVO;
import com.copyright.mall.service.product.IProductService;
import com.copyright.mall.util.wrapper.WrapMapper;
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
  public Wrapper<List<ProductSearchResp>> search(@RequestBody @ApiParam @Valid ProductSearchParam productSearchParam){
    return productService.search(productSearchParam);
  }

  @ApiOperation(value = "商品/版权域")
  @PostMapping("/area")
  @ControllerErro
  public Wrapper<AreaVO> productArea(@RequestBody @ApiParam @Valid AreaParam areaParam){
    return WrapMapper.ok(productService.getArea(areaParam));
  }


  @ApiOperation(value = "根据分类查商品")
  @PostMapping("/productByClass")
  @ControllerErro
  public Wrapper<ProductByClassVO> productByClass(@RequestBody @ApiParam @Valid ProductByClassparam productByClassparam){
    return WrapMapper.ok(productService.getProductByClass(productByClassparam));
  }

}
