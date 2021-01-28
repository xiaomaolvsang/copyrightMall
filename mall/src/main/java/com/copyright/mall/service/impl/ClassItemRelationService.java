package com.copyright.mall.service.impl;

import com.copyright.mall.bean.ClassItemRelation;
import com.copyright.mall.dao.ClassItemRelationMapper;
import com.copyright.mall.service.IClassItemRelationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 *
 *
 * @author lijian
 * @date 2019-11-27 15:25
 **/
@Service
public class ClassItemRelationService implements IClassItemRelationService {

	private static Logger logger = LoggerFactory.getLogger(ClassItemRelationService.class);

	@Resource
	private ClassItemRelationMapper classItemRelationMapper;

	@Override
	public ClassItemRelation selectByPrimaryKey(Long id) {
		return classItemRelationMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Long id) {

		return classItemRelationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(ClassItemRelation classItemRelation) {
		return classItemRelationMapper.insertSelective(classItemRelation);
	}

	@Override
	public int updateByPrimaryKeySelective(ClassItemRelation classItemRelation) {
		return classItemRelationMapper.updateByPrimaryKeySelective(classItemRelation);
	}

	@Override
	public Long selectObjectListPageTotal(ClassItemRelation classItemRelation) {
		return classItemRelationMapper.selectObjectListPageTotal(classItemRelation);
	}

	@Override
	public List<ClassItemRelation> selectObjectListPage(ClassItemRelation classItemRelation) {
		return classItemRelationMapper.selectObjectListPage(classItemRelation);
	}

	@Override
	public List<ClassItemRelation> selectByObjectList(ClassItemRelation classItemRelation){
		return classItemRelationMapper.selectByObjectList(classItemRelation);
	}

	@Override
	public int deleteByItemId(Long id) {
		return classItemRelationMapper.deleteByItemId(id);
	}

	@Override
	public List<ClassItemRelation> selectByIdsList(List<Long> ids) {
		return classItemRelationMapper.selectByIdsList(ids);
	}


}
