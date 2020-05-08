package com.copyright.mall.service;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.Kv;



/**
 * 
 * 
 * @author lijian
 * @date 2020-05-08 14:36
 **/
public interface IKvService {

	public Kv selectByPrimaryKey(Integer id);

	public int deleteByPrimaryKey(Integer id);

	public int insertSelective(Kv kv);

	public int updateByPrimaryKeySelective(Kv kv);

	public Long selectObjectListPageTotal(Kv kv);

	public List<Kv> selectObjectListPage(Kv kv);

	public List<Kv> selectByObjectList(Kv kv);

}
