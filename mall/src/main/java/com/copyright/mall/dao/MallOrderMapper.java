package com.copyright.mall.dao;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.MallOrder;



/**
 * 
 * 结算单
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public interface MallOrderMapper {

	public MallOrder selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(MallOrder mallOrder);

	public int updateByPrimaryKeySelective(MallOrder mallOrder);

	public Long selectObjectListPageTotal(MallOrder mallOrder);

	public List<MallOrder> selectObjectListPage(MallOrder mallOrder);

	public List<MallOrder> selectByObjectList(MallOrder mallOrder);

}
