package com.copyright.mall.dao;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.LikeOpusRelation;



/**
 * 
 * 
 * @author lijian
 * @date 2020-06-13 12:26
 **/
public interface LikeOpusRelationMapper {

	public LikeOpusRelation selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(LikeOpusRelation likeOpusRelation);

	public int updateByPrimaryKeySelective(LikeOpusRelation likeOpusRelation);

	public Long selectObjectListPageTotal(LikeOpusRelation likeOpusRelation);

	public List<LikeOpusRelation> selectObjectListPage(LikeOpusRelation likeOpusRelation);

	public List<LikeOpusRelation> selectByObjectList(LikeOpusRelation likeOpusRelation);

}
