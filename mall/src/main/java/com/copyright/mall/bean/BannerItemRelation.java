package com.copyright.mall.bean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author lijian
 * @date 2019-12-03 10:45
 **/
public class BannerItemRelation implements Serializable {

	private static final long serialVersionUID = 8058109909316119835L;

	/**主键**/
	private Long id;

	/**bannerid**/
	private Long bannerId;

	/**itemid**/
	private Long itemId;

	/**数据名称**/
	private String dataName;

	/**数据图片**/
	private String dataImg;

	/**拓展字段1**/
	private String expand1;



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

	public void setDataName(String dataName){
		this.dataName = dataName;
	}

	public String getDataName(){
		return this.dataName;
	}

	public void setDataImg(String dataImg){
		this.dataImg = dataImg;
	}

	public String getDataImg(){
		return this.dataImg;
	}

	public void setExpand1(String expand1){
		this.expand1 = expand1;
	}

	public String getExpand1(){
		return this.expand1;
	}

	@Override
	public String toString() {
		return "BannerItemRelation [ id= "+id+
			",bannerId= "+bannerId+
			",itemId= "+itemId+
			",dataName= "+dataName+
			",dataImg= "+dataImg+
			",expand1= "+expand1+"]";
	}
}
