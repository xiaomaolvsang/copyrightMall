package com.copyright.mall.manage.controller;

import com.alibaba.fastjson.JSON;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "后端基础工具")
@Slf4j
@RestController
@RequestMapping("/manage/v1/basic/tool")
public class BasicToolController {

    Configuration cfg = new Configuration(Region.region1());
    UploadManager uploadManager = new UploadManager(cfg);
    String accessKey = "Twxm2kFR7QB1jjP-ewSZGDGi42w9eDOoG8j5btFR";
    String secretKey = "cUCYDH2qCX6gKwdGg_qEHAuWMF4DYFtxeRz6RCAu";
    String bucket = "beartcenter";
    String key = null;

    @PostMapping("/uploadImg")
    public Wrapper<String> uploadImg(@RequestParam("fileName") MultipartFile file){
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(file.getInputStream(),key,upToken,null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = JSON.toJavaObject(JSON.parseObject(response.bodyString()), DefaultPutRet.class);
            return WrapMapper.ok("http://img.beartcenter.com/"+putRet.key);
        } catch (Exception ex) {
            log.error("upload error");
            return WrapMapper.error(String.format("上传失败 %s", ex.getMessage()));
        }
    }
}
