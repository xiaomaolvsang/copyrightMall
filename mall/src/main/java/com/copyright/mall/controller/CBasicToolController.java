package com.copyright.mall.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copyright.mall.service.JwtService;
import com.copyright.mall.util.QRcodeUtil;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "小程序基础工具")
@Slf4j
@RestController
@RequestMapping("/v1/basic/tool")
public class CBasicToolController {

    Configuration cfg = new Configuration(Region.region1());
    UploadManager uploadManager = new UploadManager(cfg);
    String accessKey = "Twxm2kFR7QB1jjP-ewSZGDGi42w9eDOoG8j5btFR";
    String secretKey = "cUCYDH2qCX6gKwdGg_qEHAuWMF4DYFtxeRz6RCAu";
    String bucket = "beartcenter";
    String key = null;

    @Resource
    private HttpServletResponse response;

    @PostMapping("/uploadImg")
    @ApiOperation("图片上传")
    public Wrapper<String> uploadImg(@RequestParam("fileName") MultipartFile file){
        StringMap putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":\"$(fsize)}\"" +
                ",\"width\":\"${imageInfo.width}\",\"height\":\"${imageInfo.height}\"}");
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket, null, 3600, putPolicy);
        try {
            Response response = uploadManager.put(file.getInputStream(),key,upToken,null, null);
            //解析上传成功的结果
            JSONObject putRet = JSON.parseObject(response.bodyString());
            return WrapMapper.ok("http://img.798ipartstore.com/"+putRet.getString("key")+"?width="+putRet.getString("width")
                    +"&height="+putRet.getString("height"));
        } catch (Exception ex) {
            log.error("upload error");
            return WrapMapper.error(String.format("上传失败 %s", ex.getMessage()));
        }
    }

    @GetMapping("/QRCode")
    @ApiOperation("生成二维码")
    public void QRCode(String text,Integer width, Integer height){
        QRcodeUtil.encodeQRCode(text,response,width,height);
    }

    public static void main(String[] args) {
        System.out.println(        new JwtService().isTokenExpired("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJvQ3hlcTVmQ1h0TmhWY3JrSmpFSkh5Q3kyRlFVIiwidXNlcl9pZCI6Im9DeGVxNWZDWHROaFZjcmtKakVKSHlDeTJGUVUiLCJleHAiOjE1OTIxMjQ5MzYsImlhdCI6MTU5MTg4NDkzNn0.BslVpDdNl_FFeh7OjrJRddqHtzk6a796wKPAyKZ7S_I"));    }
}
