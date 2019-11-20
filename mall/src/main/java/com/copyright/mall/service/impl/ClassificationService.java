package com.copyright.mall.service.impl;

import com.copyright.mall.bean.Classification;
import com.copyright.mall.bean.Shop;
import com.copyright.mall.bean.resp.classification.ClassResp;
import com.copyright.mall.config.GuavaManage;
import com.copyright.mall.dao.ClassificationMapper;
import com.copyright.mall.domain.requeest.classification.ClassParam;
import com.copyright.mall.service.IClassificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 *
 *
 * @author lijian
 * @date 2019-11-20 15:16
 **/
@Service
public class ClassificationService implements IClassificationService {

	private static Logger logger = LoggerFactory.getLogger(ClassificationService.class);

	@Resource
	private ClassificationMapper classificationMapper;

  @Resource
  private GuavaManage guavaManage;

	@Override
	public Classification selectByPrimaryKey(Long id) {
		return classificationMapper.selectByPrimaryKey(id);
	}
	@Override
	public int deleteByPrimaryKey(Long id) {

		return classificationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Classification classification) {
		return classificationMapper.insertSelective(classification);
	}

	@Override
	public int updateByPrimaryKeySelective(Classification classification) {
		return classificationMapper.updateByPrimaryKeySelective(classification);
	}

	@Override
	public Long selectObjectListPageTotal(Classification classification) {
		return classificationMapper.selectObjectListPageTotal(classification);
	}

	@Override
	public List<Classification> selectObjectListPage(Classification classification) {
		return classificationMapper.selectObjectListPage(classification);
	}

	@Override
	public List<Classification> selectByObjectList(Classification classification){
		return classificationMapper.selectByObjectList(classification);
	}

  @Override
  public List<ClassResp> getClassification(ClassParam classParam) {
    List<Classification> classifications = getAll();
    List<ClassResp> list = new ArrayList<>();
    classifications.stream()
      .filter(classification ->
        classification.getMallId().equals(classParam.getMallId())
          && classification.getUpperId() == 0)
      .forEach(classification -> {
        ClassResp classResp = new ClassResp();
        classResp.setFirstCategoryName(classification.getClassName());
        classResp.setFirstCategoryId(classification.getId());
        list.add(classResp);
      });
    return list;
  }

  private List<Classification> getAll(){
	  Classification classification = new Classification();
    Optional<Object> infoOptional = guavaManage.getCache(getKey(),
      () -> Optional.ofNullable(selectByObjectList(classification)));
    List<Classification> result = new ArrayList<>();
    if (infoOptional.isPresent()) {
      result = (List<Classification>) infoOptional.get();
    }
    return result;
  }

  private String getKey() {
    return "class";
  }
}
