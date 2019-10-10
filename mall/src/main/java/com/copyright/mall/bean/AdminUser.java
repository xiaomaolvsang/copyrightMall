package com.copyright.mall.bean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 菜单权限 + 数据权限
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public class AdminUser implements Serializable {

	private static final long serialVersionUID = 6441078748351490914L;

	/**主键**/
	private Long id;

	/**创建时间**/
	private Date gmtCreate;

	/**修改时间**/
	private Date gmtModified;

	/**菜单权限组**/
	private String adGroup;

	/**菜单名**/
	private String name;

	/****/
	private String pwd;



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

	public void setAdGroup(String adGroup){
		this.adGroup = adGroup;
	}

	public String getAdGroup(){
		return this.adGroup;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setPwd(String pwd){
		this.pwd = pwd;
	}

	public String getPwd(){
		return this.pwd;
	}

	@Override
	public String toString() {
		return "AdminUser [ id= "+id+
			",gmtCreate= "+gmtCreate+
			",gmtModified= "+gmtModified+
			",adGroup= "+adGroup+
			",name= "+name+
			",pwd= "+pwd+"]";
	}
}
