package com.copyright.mall.dao;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.ClassItemRelation;
import org.apache.ibatis.annotations.Param;


/**
 * 
 * 
 * @author lijian
 * @date 2019-11-27 15:25
 **/
public interface ClassItemRelationMapper {

	public ClassItemRelation selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(ClassItemRelation classItemRelation);

	public int updateByPrimaryKeySelective(ClassItemRelation classItemRelation);

	public Long selectObjectListPageTotal(ClassItemRelation classItemRelation);

	public List<ClassItemRelation> selectObjectListPage(ClassItemRelation classItemRelation);

	public List<ClassItemRelation> selectByObjectList(ClassItemRelation classItemRelation);

	public int deleteByItemId(Long id);

	public List<ClassItemRelation> selectByIdsList(@Param("ids") List<Long> ids);

}
