package com.copyright.mall.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.copyright.mall.dao.MallOrderMapper;
import com.copyright.mall.service.IMallOrderService;

import com.copyright.mall.bean.MallOrder;


/**
 *
 * 结算单
 * @author lijian
 * @date 2019-10-10 16:30
 **/
@Service
public class MallOrderService implements IMallOrderService {

	private static Logger logger = LoggerFactory.getLogger(MallOrderService.class);

	@Resource
	private MallOrderMapper mallOrderMapper;

	@Override
	public MallOrder selectByPrimaryKey(Long id) {
		return mallOrderMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Long id) {

		return mallOrderMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(MallOrder mallOrder) {
		return mallOrderMapper.insertSelective(mallOrder);
	}

	@Override
	public int updateByPrimaryKeySelective(MallOrder mallOrder) {
		return mallOrderMapper.updateByPrimaryKeySelective(mallOrder);
	}

	@Override
	public Long selectObjectListPageTotal(MallOrder mallOrder) {
		return mallOrderMapper.selectObjectListPageTotal(mallOrder);
	}

	@Override
	public List<MallOrder> selectObjectListPage(MallOrder mallOrder) {
		return mallOrderMapper.selectObjectListPage(mallOrder);
	}

	@Override
	public List<MallOrder> selectByObjectList(MallOrder mallOrder){
		return mallOrderMapper.selectByObjectList(mallOrder);
	}

	@Override
	public MallOrder selectByMallOrderID(String mallOrderId) {
		MallOrder mallOrder = new MallOrder();
		mallOrder.setMallOrderId(mallOrderId);
		return mallOrder;
	}

}
