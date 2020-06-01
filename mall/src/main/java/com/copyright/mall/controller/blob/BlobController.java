package com.copyright.mall.controller.blob;

import com.copyright.mall.bean.Kv;
import com.copyright.mall.controller.BaseController;
import com.copyright.mall.domain.vo.blob.BlobVO;
import com.copyright.mall.service.IKvService;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : zhangyuchen
 * @date : 2019/11/18 15:31
 */
@Api(tags = "内容接口")
@Slf4j
@RestController
@RequestMapping("/V2/blob")
public class BlobController extends BaseController {

    @Resource
    private IKvService kvService;

    @GetMapping("/content/{id}")
    public Wrapper<BlobVO> content(@PathVariable("id") Integer id){
        Kv kv = kvService.selectByPrimaryKey(id);
        if(kv == null){
            return WrapMapper.wrap(1,"不存在");
        }
        BlobVO blobVO = new BlobVO();
        blobVO.setContent(kv.getContent());
        blobVO.setTitle(kv.getBlobTitle());
        blobVO.setCreateTime(kv.getStartTime());
        return WrapMapper.ok(blobVO);
    }

}
