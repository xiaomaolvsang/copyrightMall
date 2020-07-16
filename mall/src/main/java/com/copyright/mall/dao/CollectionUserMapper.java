package com.copyright.mall.dao;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.CollectionUser;
import org.apache.ibatis.annotations.Param;


/**
 * 
 * 
 * @author lijian
 * @date 2020-07-15 23:18
 **/
public interface CollectionUserMapper {

	public CollectionUser selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(CollectionUser collectionUser);

	public int updateByPrimaryKeySelective(CollectionUser collectionUser);

	public Long selectObjectListPageTotal(CollectionUser collectionUser);

	public List<CollectionUser> selectObjectListPage(CollectionUser collectionUser);

	public List<CollectionUser> selectByObjectList(CollectionUser collectionUser);

	public int deleteByUserIdAndOpusId(@Param("userId") Long userId,
									   @Param("opusId") Long opusId);
}
