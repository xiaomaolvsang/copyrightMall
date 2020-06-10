package com.copyright.mall.controller;

import com.copyright.mall.bean.Certificate;
import com.copyright.mall.bean.Copyright;
import com.copyright.mall.bean.User;
import com.copyright.mall.domain.dto.copyright.CertificateDetail;
import com.copyright.mall.domain.dto.copyright.CopyrightCreateParam;
import com.copyright.mall.domain.dto.copyright.CopyrightQueryParam;
import com.copyright.mall.domain.dto.copyright.TimeLineDTO;
import com.copyright.mall.domain.vo.copyright.CertificateVO;
import com.copyright.mall.enums.CopyRightStatusEnum;
import com.copyright.mall.manage.domain.dto.AuthorizationParam;
import com.copyright.mall.service.ICertificateService;
import com.copyright.mall.service.ICopyrightService;
import com.copyright.mall.service.IUserService;
import com.copyright.mall.util.BeanMapperUtils;
import com.copyright.mall.util.IDUtil;
import com.copyright.mall.util.wrapper.WrapMapper;
import com.copyright.mall.util.wrapper.Wrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Api(tags = "版权接口")
@Slf4j
@RestController
@RequestMapping("/V1/copyright")
public class CopyrightController extends BaseController{

    @Resource
    private ICopyrightService copyrightService;

    @Resource
    private ICertificateService certificateService;

    @Resource
    private IUserService userService;

    @PostMapping("/create")
    @ApiOperation("创建确权")
    public Wrapper<String> createCopyright(@RequestBody @Valid CopyrightCreateParam copyrightCreateParam){
        Copyright copyright = BeanMapperUtils.map(copyrightCreateParam,Copyright.class);
        try{
            String copyrightId = IDUtil.generatorID("");
            String certificateId = IDUtil.generatorID("");
            copyright.setCopyrightId(copyrightId);
            copyright.setAuditStatus(CopyRightStatusEnum.APPLYING.getCode());
            copyrightService.insertSelective(copyright);
            Certificate certificate = new Certificate();
            certificate.setCertificateId(certificateId);
            certificate.setPcertificateId(certificateId);
            certificate.setCopyrightId(copyrightId);
            certificate.setCerificateStatus(CopyRightStatusEnum.APPLYING.getCode());

            TimeLineDTO timeLineDTO = new TimeLineDTO();
            TimeLineDTO.TimelineItem timelineItem = new TimeLineDTO.TimelineItem();
            timelineItem.setTime(new Date());
            timelineItem.setEvent("提交申请");
            timeLineDTO.appendItem(timelineItem);
            certificate.setTimeLine(timeLineDTO.toBaseString());
            certificate.setAuthorizedPerson(this.getUserId().toString());
            certificate.setAuthorizer(this.getUserId().toString());
            certificate.setAuthorizerName(copyrightCreateParam.getCopyrightOwner());
            certificate.setType(0);
            certificateService.insertSelective(certificate);
        } catch (Exception e){
            log.error("copyright insert error ",e);
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok();
    }

    @GetMapping("/list")
    @ApiOperation("获取证书列表")
    public Wrapper<PageInfo<CertificateVO>> list( @Valid CopyrightQueryParam queryParam){
        Certificate certificate = new Certificate();
        certificate.setAuthorizedPerson(this.getUserPhone());
        certificate.setAuthorizedPerson(this.getUserId().toString());
        Page<Copyright> page = PageHelper.startPage(queryParam.getPageNum(),queryParam.getPageSize());
        List<CertificateDetail> copyrights = certificateService.selectListDetail(certificate);
        List<CertificateVO> copyrightVOS = copyrights.stream().map(this::toVO).collect(Collectors.toList());
        PageInfo<CertificateVO> copyrightVoPageInfo = PageInfo.of(copyrightVOS);
        copyrightVoPageInfo.setTotal(page.getTotal());
        return WrapMapper.ok(copyrightVoPageInfo);
    }

    @GetMapping("/detail/{certificateId}")
    @ApiOperation("证书详情")
    public Wrapper<CertificateVO> detail(@PathVariable("certificateId") String certificateId){
        Certificate certificate = new Certificate();
        certificate.setCertificateId(certificateId);
        List<CertificateDetail> list = certificateService.selectListDetail(certificate);
        CertificateDetail certificateDetail = null;
        if(!CollectionUtils.isEmpty(list)){
            certificateDetail = list.get(0);
        }
        return WrapMapper.ok(toDecodeVO(certificateDetail));
    }

    @PostMapping("/authorization")
    @ApiOperation("授权")
    public Wrapper<Boolean> authorization (@RequestBody @Valid AuthorizationParam authorizationParam){
        User user = userService.selectByPhone(authorizationParam.getPhone());
        if(user == null){
            return WrapMapper.error("被授权用户不存在");
        }
        Certificate checkParam = new Certificate();
        checkParam.setPcertificateId(authorizationParam.getPCertificateId());
        checkParam.setAuthorizedPerson(user.getId().toString());
        checkParam.setCerificateStatus(CopyRightStatusEnum.AUTHORIZED.getCode());
        List<Certificate> checkExists = certificateService.selectByObjectList(checkParam);
        if(!CollectionUtils.isEmpty(checkExists)){
            return WrapMapper.error("当前被授权用户已存在授权");
        }
        CertificateDetail pCertificateDetail = certificateService.selectByCertificateId(authorizationParam.getPCertificateId());
        Certificate certificate = new Certificate();
        certificate.setCertificateId(IDUtil.generatorID(""));
        certificate.setPcertificateId(authorizationParam.getPCertificateId());
        certificate.setCopyrightId(pCertificateDetail.getCopyrightId());
        certificate.setCerificateStatus(CopyRightStatusEnum.AUTHORIZED.getCode());
        certificate.setAuthorizer(pCertificateDetail.getCopyrightOwner());
        TimeLineDTO timeLineDTO = TimeLineDTO.fromBaseStr(pCertificateDetail.getTimeLine());
        TimeLineDTO.TimelineItem item = new TimeLineDTO.TimelineItem();
        item.setTime(new Date());
        item.setEvent("版权链授权");
        timeLineDTO.appendItem(item);
        certificate.setTimeLine(timeLineDTO.toBaseString());
        certificate.setAuthorizedPerson(user.getId().toString());
        certificate.setType(1);
        certificate.setAuthorizationDate(new Date());
        certificate.setClosingDate(authorizationParam.getClosingDate());
        certificate.setAuthorizationType(authorizationParam.getType().toString());
        certificate.setAuthorizerName(authorizationParam.getName());
        certificateService.insertSelective(certificate);
        return WrapMapper.ok(true);
    }


    private CertificateVO toVO(CertificateDetail certificate){
        CertificateVO copyrightVO = BeanMapperUtils.map(certificate,CertificateVO.class);
        copyrightVO.setTimeLine(certificate.getTimeLine());
        copyrightVO.setCerificateStatusDesc(Objects.requireNonNull(CopyRightStatusEnum.valueOf(certificate.getCerificateStatus())).getDesc());
        return copyrightVO;
    }

    private CertificateVO toDecodeVO(CertificateDetail certificate){
        CertificateVO certificateVO = BeanMapperUtils.map(certificate,CertificateVO.class);
        certificateVO.setCerificateStatusDesc(Objects.requireNonNull(CopyRightStatusEnum.valueOf(certificate.getCerificateStatus())).getDesc());
        certificateVO.setTimeLineDTO(TimeLineDTO.fromBaseStr(certificate.getTimeLine()));
        return certificateVO;
    }
}
