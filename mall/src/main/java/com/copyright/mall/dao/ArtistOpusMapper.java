package com.copyright.mall.dao;

import java.util.List;
import java.util.Date;
import java.util.Map;

import com.copyright.mall.bean.ArtistOpus;
import org.apache.ibatis.annotations.Param;


/**
 * 
 * 
 * @author lijian
 * @date 2019-11-28 13:57
 **/
public interface ArtistOpusMapper {

	public ArtistOpus selectByPrimaryKey(Long id);

	public int deleteByPrimaryKey(Long id);

	public int insertSelective(ArtistOpus artistOpus);

	public int updateByPrimaryKeySelective(ArtistOpus artistOpus);

	public Long selectObjectListPageTotal(ArtistOpus artistOpus);

	public List<ArtistOpus> selectObjectListPage(ArtistOpus artistOpus);

	public List<ArtistOpus> selectByObjectList(ArtistOpus artistOpus);

	public List<ArtistOpus> selectByObjectListDesc(ArtistOpus artistOpus);

	public int likeOpus(Long id);

	public List<ArtistOpus> selectByShopIds(@Param("shopIds") List<Long> shopIds,@Param("opusTitle") String opusTitle);

	public List<ArtistOpus> selectByOpusIds(@Param("opusIds") List<Long> opusIds);

}
