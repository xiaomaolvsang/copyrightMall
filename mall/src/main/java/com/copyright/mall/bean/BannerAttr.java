package com.copyright.mall.bean;

import java.io.Serializable;


/**
 *
 *
 * @author lijian
 * @date 2019-11-23 19:24
 **/
public class BannerAttr implements Serializable {

	private static final long serialVersionUID = 8176972312998280871L;

	/**主键**/
	private Long id;

	/**banner主键**/
	private Long bannerId;

	/**图片列表**/
	private String image;

	/****/
	private String linkType;

	/****/
	private String targetUrl;

	/**类别名称**/
	private String categoryName;

	/**类别id**/
	private Long categoryId;

	/**标题**/
	private String title;



	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setBannerId(Long bannerId){
		this.bannerId = bannerId;
	}

	public Long getBannerId(){
		return this.bannerId;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return this.image;
	}

	public void setLinkType(String linkType){
		this.linkType = linkType;
	}

	public String getLinkType(){
		return this.linkType;
	}

	public void setTargetUrl(String targetUrl){
		this.targetUrl = targetUrl;
	}

	public String getTargetUrl(){
		return this.targetUrl;
	}

	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}

	public String getCategoryName(){
		return this.categoryName;
	}

	public void setCategoryId(Long categoryId){
		this.categoryId = categoryId;
	}

	public Long getCategoryId(){
		return this.categoryId;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return this.title;
	}

	@Override
	public String toString() {
		return "BannerAttr [ id= "+id+
			",bannerId= "+bannerId+
			",image= "+image+
			",linkType= "+linkType+
			",targetUrl= "+targetUrl+
			",categoryName= "+categoryName+
			",categoryId= "+categoryId+
			",title= "+title+"]";
	}
}
