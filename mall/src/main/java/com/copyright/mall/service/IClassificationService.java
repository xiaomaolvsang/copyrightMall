package com.copyright.mall.service;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.Classification;
import com.copyright.mall.bean.resp.classification.ClassResp;
import com.copyright.mall.domain.requeest.classification.ClassParam;
import com.copyright.mall.domain.requeest.classification.ClassTwoParam;


/**
 *
 *
 * @author lijian
 * @date 2019-11-20 15:16
 **/
public interface IClassificationService {

	public Classification selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(Classification classification);

	public int updateByPrimaryKeySelective(Classification classification);

	public Long selectObjectListPageTotal(Classification classification);

	public List<Classification> selectObjectListPage(Classification classification);

	public List<Classification> selectByObjectList(Classification classification);

	public List<ClassResp> getClassification(ClassParam classParam);

	public List<ClassResp> getClassTwo(ClassTwoParam classTwoParam);

    public List<Classification> getAll();
}
