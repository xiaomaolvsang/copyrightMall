package com.copyright.mall.dao;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.Kv;
import com.copyright.mall.manage.domain.dto.QueryBlobListParam;


/**
 * 
 * 
 * @author lijian
 * @date 2020-05-08 14:36
 **/
public interface KvMapper {

	public Kv selectByPrimaryKey(Integer id);

	public int deleteByPrimaryKey(Integer id);

	public int insertSelective(Kv kv);

	public int updateByPrimaryKeySelective(Kv kv);

	public Long selectObjectListPageTotal(Kv kv);

	public List<Kv> selectObjectListPage(Kv kv);

	public List<Kv> selectByObjectList(Kv kv);

	List<Kv> queryKVList(QueryBlobListParam queryBlobListParam);

	List<Kv> queryKVByKey(String key);

}
