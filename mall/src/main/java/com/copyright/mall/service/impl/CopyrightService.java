package com.copyright.mall.service.impl;

import com.copyright.mall.bean.Copyright;
import com.copyright.mall.config.GuavaManage;
import com.copyright.mall.dao.CopyrightMapper;
import com.copyright.mall.service.ICopyrightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * 
 * 版权表
 * @author lijian
 * @date 2020-06-10 10:35
 **/
@Service
public class CopyrightService implements ICopyrightService {

	private static Logger logger = LoggerFactory.getLogger(CopyrightService.class);

	@Resource
	private GuavaManage guavaManage;
	
	@Resource
	private CopyrightMapper copyrightMapper;

	@Override
	public Copyright selectByPrimaryKey(Long id) {
		return copyrightMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Long id) {

		return copyrightMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public int insertSelective(Copyright copyright) {
		return copyrightMapper.insertSelective(copyright);
	}

	@Override
	public int updateByPrimaryKeySelective(Copyright copyright) {
		return copyrightMapper.updateByPrimaryKeySelective(copyright);
	}

	@Override
	public Long selectObjectListPageTotal(Copyright copyright) {
		return copyrightMapper.selectObjectListPageTotal(copyright);
	}

	@Override
	public List<Copyright> selectObjectListPage(Copyright copyright) {
		return copyrightMapper.selectObjectListPage(copyright);
	}

	@Override
	public List<Copyright> selectByObjectList(Copyright copyright){
		return copyrightMapper.selectByObjectList(copyright);
	}

	@Override
	public Copyright selectByCopyRightId(String copyrightID) {
		Copyright copyright = new Copyright();
		copyright.setCopyrightId(copyrightID);
		List<Copyright> copyrights = this.selectByObjectList(copyright);
		if(CollectionUtils.isEmpty(copyrights)){
			return null;
		}
		return copyrights.stream().findFirst().orElse(null);
	}

	public List<Copyright> selectAllObject(){
		Copyright copyright = new Copyright();
		Optional<Object> infoOptional = guavaManage.getCache(getKey(),
				() -> Optional.ofNullable(selectByObjectList(copyright)));
		List<Copyright> result = new ArrayList<>();
		if (infoOptional.isPresent()) {
			result = (List<Copyright>)infoOptional.get();
		}
		return result;
	}


	private String getKey(){
		return "copyright";
	}


}
