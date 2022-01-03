package com.copyright.mall.controller;

import com.copyright.mall.bean.Certificate;
import com.copyright.mall.bean.Copyright;
import com.copyright.mall.bean.User;
import com.copyright.mall.domain.dto.copyright.CertificateDetail;
import com.copyright.mall.domain.dto.copyright.CopyrightCreateParam;
import com.copyright.mall.domain.dto.copyright.CopyrightEevokeParam;
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
import com.copyright.mall.util.Utils;
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
            copyright.setCreationTime(copyrightCreateParam.getCreationDate());
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
            certificate.setType(0);
            certificate.setAuthorizedPerson(this.getUserId().toString());
            certificate.setAuthorizedPersionName(copyrightCreateParam.getCopyrightOwner());
            certificate.setAuthorizer(this.getUserId().toString());
            certificate.setAuthorizerName(copyrightCreateParam.getCopyrightOwner());
            certificateService.insertSelective(certificate);
        } catch (Exception e){
            log.error("copyright insert error ",e);
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok();
    }

    @PostMapping("/revoke")
    @ApiOperation("撤销版权")
    public Wrapper<String> revokeCopyright(@RequestBody CopyrightEevokeParam copyrightEevokeParam) {
        Certificate queryParam = new Certificate();
        queryParam.setPcertificateId(copyrightEevokeParam.getCertificateId());
        List<Certificate> certificates = certificateService.selectByObjectList(queryParam);
        if(!CollectionUtils.isEmpty(certificates)){
            for(Certificate certificate : certificates){
                if(!certificate.getCertificateId().equals(certificate.getPcertificateId())){
                    return WrapMapper.error("不满足撤销条件");
                }
            }
        }
        certificateService.deleteByCertificateId(copyrightEevokeParam.getCertificateId());
        return WrapMapper.ok("撤销成功");
    }


    @GetMapping("/list")
    @ApiOperation("获取证书列表")
    public Wrapper<PageInfo<CertificateVO>> list( @Valid CopyrightQueryParam queryParam){
        Certificate certificate = new Certificate();
        certificate.setAuthorizedPerson(this.getUserPhone());
        certificate.setAuthorizedPerson(this.getUserId().toString());
        Page<Copyright> page = PageHelper.startPage(queryParam.getPageNum(),queryParam.getPageSize());
        List<CertificateDetail> copyrights = certificateService.selectListDetail(certificate);
        List<CertificateVO> copyrightVOS = copyrights.stream().map(CopyrightController::toVO).collect(Collectors.toList());
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
            Certificate queryParam = new Certificate();
            queryParam.setPcertificateId(certificateDetail.getCertificateId());
            List<Certificate> certificates = certificateService.selectByObjectList(queryParam);
            for(Certificate certificateItem : certificates){
                if(!certificateItem.getCertificateId().equals(certificateItem.getPcertificateId())){
                    certificateDetail.setHasSubCopyright(true);
                    break;
                }
            }
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
        checkParam.setCerificateStatus(CopyRightStatusEnum.CONFIRMED_RIGHT.getCode());
        checkExists = certificateService.selectByObjectList(checkParam);
        if(!CollectionUtils.isEmpty(checkExists)){
            return WrapMapper.error("当前被授权用户已存在授权");
        }
        CertificateDetail pCertificateDetail = certificateService.selectByCertificateId(authorizationParam.getPCertificateId());
        Certificate certificate = new Certificate();
        certificate.setCertificateId(IDUtil.generatorID(""));
        certificate.setPcertificateId(authorizationParam.getPCertificateId());
        certificate.setCopyrightId(pCertificateDetail.getCopyrightId());
        certificate.setCerificateStatus(CopyRightStatusEnum.AUTHORIZED.getCode());
        certificate.setAuthorizer(this.getUserId().toString());
        certificate.setAuthorizerName(pCertificateDetail.getCopyrightOwner());
        certificate.setAuthorizedPerson(user.getId().toString());
        certificate.setAuthorizedPersionName(authorizationParam.getName());
        TimeLineDTO timeLineDTO = TimeLineDTO.fromBaseStr(pCertificateDetail.getTimeLine());
        TimeLineDTO.TimelineItem item = new TimeLineDTO.TimelineItem();
        item.setTime(new Date());
        item.setEvent("版权链授权");
        timeLineDTO.appendItem(item);
        certificate.setTimeLine(timeLineDTO.toBaseString());

        certificate.setType(1);
        certificate.setAuthorizationDate(new Date());
        certificate.setClosingDate(authorizationParam.getClosingDate());
        certificate.setAuthorizationType(authorizationParam.getType().toString());
        certificateService.insertSelective(certificate);
        return WrapMapper.ok(true);
    }

    public static CertificateVO toVO(CertificateDetail certificate){
        CertificateVO copyrightVO = BeanMapperUtils.map(certificate,CertificateVO.class);
        copyrightVO.setTimeLine(certificate.getTimeLine());
        copyrightVO.setCerificateStatusDesc(Objects.requireNonNull(CopyRightStatusEnum.valueOf(certificate.getCerificateStatus())).getDesc());
        copyrightVO.setCertificateOfCopyrightOwnerList(Utils.toStringList(certificate.getCertificateOfCopyrightOwner()));
        copyrightVO.setPicturesOfWorksList(Utils.toStringList(certificate.getPicturesOfWorks()));
        copyrightVO.setCopyrightCertificateList(Utils.toStringList(certificate.getCopyrightCertificate()));
        copyrightVO.setCategoryList(Utils.toStringList(certificate.getCategory()));
        copyrightVO.setAuthorizationTypeList(Utils.toStringList(certificate.getAuthorizationType()));
        return copyrightVO;
    }

    private CertificateVO toDecodeVO(CertificateDetail certificate){
        if(certificate == null ){
            return new CertificateVO();
        }
        CertificateVO certificateVO = BeanMapperUtils.map(certificate,CertificateVO.class);
        if(certificate.getCerificateStatus() != null) {
            CopyRightStatusEnum copyRightStatusEnum = CopyRightStatusEnum.valueOf(certificate.getCerificateStatus());
            if (copyRightStatusEnum != null) {
                certificateVO.setCerificateStatusDesc(copyRightStatusEnum.getDesc());
            }
        }
        certificateVO.setTimeLineDTO(TimeLineDTO.fromBaseStr(certificate.getTimeLine()));
        certificateVO.setCertificateOfCopyrightOwnerList(Utils.toStringList(certificate.getCertificateOfCopyrightOwner()));
        certificateVO.setPicturesOfWorksList(Utils.toStringList(certificate.getPicturesOfWorks()));
        certificateVO.setCopyrightCertificateList(Utils.toStringList(certificate.getCopyrightCertificate()));
        certificateVO.setCategoryList(Utils.toStringList(certificate.getCategory()));
        certificateVO.setAuthorizationTypeList(Utils.toStringList(certificate.getAuthorizationType()));
        certificateVO.setHasSubCopyright(certificate.isHasSubCopyright());
        return certificateVO;
    }

    public static void main(String[] args) {
        System.out.println(TimeLineDTO.fromBaseStr("eyJpdGVtcyI6W3siZXZlbnQiOiLkv67mlLnniYjmnYMiLCJ0aW1lIjoxNjE4NjU2OTYxNzc3fSx7ImV2ZW50Ijoi54mI5p2D6ZO+6K+B5Lmm6aKB5Y+RIn1dfQ=="));
    }

}
