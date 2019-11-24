package com.copyright.mall.bean;

import java.io.Serializable;


/**
 *
 *
 * @author lijian
 * @date 2019-11-24 14:53
 **/
public class BannerItemRelation implements Serializable {

	private static final long serialVersionUID = 8750769915867814768L;

	/**主键**/
	private Long id;

	/**bannerid**/
	private Long bannerId;

	/**itemid**/
	private Long itemId;



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

	public void setItemId(Long itemId){
		this.itemId = itemId;
	}

	public Long getItemId(){
		return this.itemId;
	}

	@Override
	public String toString() {
		return "BannerItemRelation [ id= "+id+
			",bannerId= "+bannerId+
			",itemId= "+itemId+"]";
	}
}
