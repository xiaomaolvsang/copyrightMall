package com.copyright.mall.bean;

import java.io.Serializable;


/**
 *
 *
 * @author lijian
 * @date 2019-12-01 15:23
 **/
public class Banner implements Serializable {

	private static final long serialVersionUID = 410110803614712898L;

	/**主键**/
	private Long id;

	/**组建类型**/
	private String type;

	/**组建高度**/
	private Integer height;

	/**组建宽度**/
	private Integer width;

	/**商城id**/
	private Long mallId;

	/**mall or copyright**/
	private String mallType;

	/**权重**/
	private Integer topValue;



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

	public void setMallId(Long mallId){
		this.mallId = mallId;
	}

	public Long getMallId(){
		return this.mallId;
	}

	public void setMallType(String mallType){
		this.mallType = mallType;
	}

	public String getMallType(){
		return this.mallType;
	}

	public void setTopValue(Integer topValue){
		this.topValue = topValue;
	}

	public Integer getTopValue(){
		return this.topValue;
	}

	@Override
	public String toString() {
		return "Banner [ id= "+id+
			",type= "+type+
			",height= "+height+
			",width= "+width+
			",mallId= "+mallId+
			",mallType= "+mallType+
			",topValue= "+topValue+"]";
	}
}
