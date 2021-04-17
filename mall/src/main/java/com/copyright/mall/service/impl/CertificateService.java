package com.copyright.mall.service.impl;

import com.copyright.mall.bean.Certificate;
import com.copyright.mall.dao.CertificateMapper;
import com.copyright.mall.domain.dto.copyright.CertificateDetail;
import com.copyright.mall.service.ICertificateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;


/**
 * 
 * 
 * @author lijian
 * @date 2020-06-10 11:58
 **/
@Service
public class CertificateService implements ICertificateService {

	private static Logger logger = LoggerFactory.getLogger(CertificateService.class);

	@Resource
	private CertificateMapper certificateDao;

	@Override
	public Certificate selectByPrimaryKey(Integer id) {
		return certificateDao.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Integer id) {

		return certificateDao.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCertificateId(String certificateId){
		return certificateDao.deleteByCertificateId(certificateId);
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public int insertSelective(Certificate certificate) {
		return certificateDao.insertSelective(certificate);
	}

	@Override
	public int updateByPrimaryKeySelective(Certificate certificate) {
		return certificateDao.updateByPrimaryKeySelective(certificate);
	}

	@Override
	public int updateByCertificateIdSelective(Certificate certificate) {
		return certificateDao.updateByPrimaryKeySelective(certificate);
	}

	@Override
	public Long selectObjectListPageTotal(Certificate certificate) {
		return certificateDao.selectObjectListPageTotal(certificate);
	}

	@Override
	public List<Certificate> selectObjectListPage(Certificate certificate) {
		return certificateDao.selectObjectListPage(certificate);
	}

	@Override
	public List<Certificate> selectByObjectList(Certificate certificate){
		return certificateDao.selectByObjectList(certificate);
	}

	@Override
	public List<CertificateDetail> selectListDetail(Certificate certificate) {
		return certificateDao.selectDetail(certificate);
	}

	@Override
	public CertificateDetail selectByCertificateId(String certificateId) {
		Certificate certificate = new Certificate();
		certificate.setCertificateId(certificateId);
		List<CertificateDetail> certificateDetails = certificateDao.selectDetail(certificate);
		return CollectionUtils.isEmpty(certificateDetails)?null:certificateDetails.get(0);
	}

}
