package com.copyright.mall.dao;

import com.copyright.mall.bean.Certificate;
import com.copyright.mall.domain.dto.copyright.CertificateDetail;

import java.util.List;



/**
 * 
 * 
 * @author lijian
 * @date 2020-06-10 10:35
 **/
public interface CertificateMapper {

	public Certificate selectByPrimaryKey(Integer id);

	public int deleteByPrimaryKey(Integer id);

	public int insertSelective(Certificate certificate);

	public int updateByPrimaryKeySelective(Certificate certificate);

	public Long selectObjectListPageTotal(Certificate certificate);

	public List<Certificate> selectObjectListPage(Certificate certificate);

	public List<Certificate> selectByObjectList(Certificate certificate);

	List<CertificateDetail> selectDetail(Certificate certificate);

}
