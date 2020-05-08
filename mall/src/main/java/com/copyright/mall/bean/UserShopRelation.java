package com.copyright.mall.bean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author lijian
 * @date 2020-05-08 12:50
 **/
public class UserShopRelation implements Serializable {

	private static final long serialVersionUID = 3013596295209738400L;

	/**主键**/
	private Long id;

	/**商铺id**/
	private Long shopId;

	/**用户id**/
	private Long userId;



	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setShopId(Long shopId){
		this.shopId = shopId;
	}

	public Long getShopId(){
		return this.shopId;
	}

	public void setUserId(Long userId){
		this.userId = userId;
	}

	public Long getUserId(){
		return this.userId;
	}

	@Override
	public String toString() {
		return "UserShopRelation [ id= "+id+
			",shopId= "+shopId+
			",userId= "+userId+"]";
	}
}
