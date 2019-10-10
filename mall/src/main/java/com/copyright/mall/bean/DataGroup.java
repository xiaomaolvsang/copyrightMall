package com.copyright.mall.bean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 数据权限
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public class DataGroup implements Serializable {

	private static final long serialVersionUID = 7845881252169961270L;

	/**主键**/
	private Long id;

	/**创建时间**/
	private Date gmtCreate;

	/**修改时间**/
	private Date gmtModified;

	/**商铺id**/
	private Long shopId;

	/**组**/
	private String adGroup;

	/**组用户**/
	private String adUserId;



	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setGmtCreate(Date gmtCreate){
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtCreate(){
		return this.gmtCreate;
	}

	public void setGmtModified(Date gmtModified){
		this.gmtModified = gmtModified;
	}

	public Date getGmtModified(){
		return this.gmtModified;
	}

	public void setShopId(Long shopId){
		this.shopId = shopId;
	}

	public Long getShopId(){
		return this.shopId;
	}

	public void setAdGroup(String adGroup){
		this.adGroup = adGroup;
	}

	public String getAdGroup(){
		return this.adGroup;
	}

	public void setAdUserId(String adUserId){
		this.adUserId = adUserId;
	}

	public String getAdUserId(){
		return this.adUserId;
	}

	@Override
	public String toString() {
		return "DataGroup [ id= "+id+
			",gmtCreate= "+gmtCreate+
			",gmtModified= "+gmtModified+
			",shopId= "+shopId+
			",adGroup= "+adGroup+
			",adUserId= "+adUserId+"]";
	}
}
