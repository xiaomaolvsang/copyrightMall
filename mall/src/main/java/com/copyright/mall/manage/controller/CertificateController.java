package com.copyright.mall.manage.controller;

import com.copyright.mall.bean.Certificate;
import com.copyright.mall.bean.Copyright;
import com.copyright.mall.domain.dto.copyright.CertificateDetail;
import com.copyright.mall.domain.dto.copyright.TimeLineDTO;
import com.copyright.mall.enums.CopyRightStatusEnum;
import com.copyright.mall.manage.domain.dto.CertificateParam;
import com.copyright.mall.service.ICertificateService;
import com.copyright.mall.service.ICopyrightService;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Api(tags = "后端版权查询")
@Slf4j
@RestController
@RequestMapping("/manage/v1/certificate")
public class CertificateController {

    @Resource
    private ICertificateService certificateService;

    @Resource
    private ICopyrightService copyrightService;

    @PostMapping("/examine")
    @ApiOperation("授权")
    public Wrapper<Boolean> examine(@RequestBody @Valid CertificateParam certificateParam){
        if("certificate".equals(certificateParam.getType())){
            Copyright copyright = new Copyright();
            copyright.setId(Long.valueOf(certificateParam.getId()));
            if(0==certificateParam.getStatus()) {
                copyright.setAuditStatus(CopyRightStatusEnum.AUTHORIZED.getCode());
            }
            if(1 == certificateParam.getStatus()){
                copyright.setAuditStatus(CopyRightStatusEnum.REJECTED.getCode());
            }
            copyrightService.updateByPrimaryKeySelective(copyright);
            Certificate certificate = new Certificate();
            certificate.setId(Integer.valueOf(certificateParam.getId()));
            List<CertificateDetail> checkList  = certificateService.selectListDetail(certificate);
            if(!CollectionUtils.isEmpty(checkList)){
                CertificateDetail check = checkList.get(0);
                TimeLineDTO timeLineDTO = TimeLineDTO.fromBaseStr(check.getTimeLine());
                TimeLineDTO.TimelineItem item = new TimeLineDTO.TimelineItem();
                item.setTime(new Date());
                if(0==certificateParam.getStatus()) {
                    if (check.getType() == 0) {
                        certificate.setCerificateStatus(CopyRightStatusEnum.CONFIRMED_RIGHT.getCode());
                        item.setEvent("版权链证书颁发");
                    } else {
                        certificate.setCerificateStatus(CopyRightStatusEnum.AUTHORIZED.getCode());
                        item.setEvent("版权链证书颁发");
                    }
                }else{
                    certificate.setCerificateStatus(CopyRightStatusEnum.REJECTED.getCode());
                    item.setEvent("授权驳回");
                }
                timeLineDTO.appendItem(item);
                certificate.setTimeLine(timeLineDTO.toBaseString());
                certificateService.updateByPrimaryKeySelective(certificate);
            }
        }
        return WrapMapper.ok(true);
    }
}
