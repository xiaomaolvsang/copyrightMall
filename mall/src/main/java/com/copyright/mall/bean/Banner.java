package com.copyright.mall.bean;

import java.io.Serializable;
import java.util.List;


/**
 *
 *
 * @author lijian
 * @date 2019-11-23 19:24
 **/
public class Banner implements Serializable {

	private static final long serialVersionUID = 1360279120433327388L;

	/**主键**/
	private Long id;

	/**组建类型**/
	private String type;

	/**组建高度**/
	private Integer height;

	/**组建宽度**/
	private Integer width;

  /**商城Id**/
	private Long mallId;

	private String mallType;

	private List<BannerAttr> bannerAttrs;


	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return this.type;
	}

	public void setHeight(Integer height){
		this.height = height;
	}

	public Integer getHeight(){
		return this.height;
	}

	public void setWidth(Integer width){
		this.width = width;
	}

	public Integer getWidth(){
		return this.width;
	}

  public Long getMallId() {
    return mallId;
  }

  public void setMallId(Long mallId) {
    this.mallId = mallId;
  }

  public String getMallType() {
    return mallType;
  }

  public void setMallType(String mallType) {
    this.mallType = mallType;
  }

  public List<BannerAttr> getBannerAttrs() {
    return bannerAttrs;
  }

  public void setBannerAttrs(List<BannerAttr> bannerAttrs) {
    this.bannerAttrs = bannerAttrs;
  }

  @Override
	public String toString() {
		return "Banner [ id= "+id+
			",type= "+type+
			",height= "+height+
			",width= "+width+"]";
	}
}
