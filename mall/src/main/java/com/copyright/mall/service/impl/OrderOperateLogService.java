package com.copyright.mall.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.copyright.mall.dao.OrderOperateLogMapper;
import com.copyright.mall.service.IOrderOperateLogService;

import com.copyright.mall.bean.OrderOperateLog;


/**
 *
 * 订单流水
 * @author lijian
 * @date 2019-10-10 16:30
 **/
@Service
public class OrderOperateLogService implements IOrderOperateLogService {

	private static Logger logger = LoggerFactory.getLogger(OrderOperateLogService.class);

	@Resource
	private OrderOperateLogMapper orderOperateLogMapper;

	@Override
	public OrderOperateLog selectByPrimaryKey(Long id) {
		return orderOperateLogMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Long id) {

		return orderOperateLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(OrderOperateLog orderOperateLog) {
		return orderOperateLogMapper.insertSelective(orderOperateLog);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderOperateLog orderOperateLog) {
		return orderOperateLogMapper.updateByPrimaryKeySelective(orderOperateLog);
	}

	@Override
	public Long selectObjectListPageTotal(OrderOperateLog orderOperateLog) {
		return orderOperateLogMapper.selectObjectListPageTotal(orderOperateLog);
	}

	@Override
	public List<OrderOperateLog> selectObjectListPage(OrderOperateLog orderOperateLog) {
		return orderOperateLogMapper.selectObjectListPage(orderOperateLog);
	}

	@Override
	public List<OrderOperateLog> selectByObjectList(OrderOperateLog orderOperateLog){
		return orderOperateLogMapper.selectByObjectList(orderOperateLog);
	}

}
