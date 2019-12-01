package com.copyright.mall.bean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 用户表
 * @author lijian
 * @date 2019-12-01 11:22
 **/
public class User implements Serializable {

	private static final long serialVersionUID = 1096882501382259628L;

	/**主键**/
	private Long id;

	/**创建时间**/
	private Date gmtCreate;

	/**修改时间**/
	private Date gmtModified;

	/**电话**/
	private String phone;

	/**头像**/
	private String img;

	/****/
	private String sessionKey;

	/****/
	private String openId;



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

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return this.phone;
	}

	public void setImg(String img){
		this.img = img;
	}

	public String getImg(){
		return this.img;
	}

	public void setSessionKey(String sessionKey){
		this.sessionKey = sessionKey;
	}

	public String getSessionKey(){
		return this.sessionKey;
	}

	public void setOpenId(String openId){
		this.openId = openId;
	}

	public String getOpenId(){
		return this.openId;
	}

	@Override
	public String toString() {
		return "User [ id= "+id+
			",gmtCreate= "+gmtCreate+
			",gmtModified= "+gmtModified+
			",phone= "+phone+
			",img= "+img+
			",sessionKey= "+sessionKey+
			",openId= "+openId+"]";
	}
}
