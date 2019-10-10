package com.copyright.mall.service;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.OrderOperateLog;



/**
 *
 * 订单流水
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public interface IOrderOperateLogService {

	public OrderOperateLog selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(OrderOperateLog orderOperateLog);

	public int updateByPrimaryKeySelective(OrderOperateLog orderOperateLog);

	public Long selectObjectListPageTotal(OrderOperateLog orderOperateLog);

	public List<OrderOperateLog> selectObjectListPage(OrderOperateLog orderOperateLog);

	public List<OrderOperateLog> selectByObjectList(OrderOperateLog orderOperateLog);

}
