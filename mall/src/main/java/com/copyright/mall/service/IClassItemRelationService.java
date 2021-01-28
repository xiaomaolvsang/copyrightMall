package com.copyright.mall.service;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.ClassItemRelation;



/**
 *
 *
 * @author lijian
 * @date 2019-11-27 15:25
 **/
public interface IClassItemRelationService {

	public ClassItemRelation selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(ClassItemRelation classItemRelation);

	public int updateByPrimaryKeySelective(ClassItemRelation classItemRelation);

	public Long selectObjectListPageTotal(ClassItemRelation classItemRelation);

	public List<ClassItemRelation> selectObjectListPage(ClassItemRelation classItemRelation);

	public List<ClassItemRelation> selectByObjectList(ClassItemRelation classItemRelation);

	public int deleteByItemId(Long id);

	public List<ClassItemRelation> selectByIdsList(List<Long> ids);
}
