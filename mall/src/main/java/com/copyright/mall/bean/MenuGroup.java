package com.copyright.mall.bean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 组权限
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public class MenuGroup implements Serializable {

	private static final long serialVersionUID = 3589443948339393474L;

	/**主键**/
	private Long id;

	/**创建时间**/
	private Date gmtCreate;

	/**修改时间**/
	private Date gmtModified;

	/**菜单id**/
	private String menuId;

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

	public void setMenuId(String menuId){
		this.menuId = menuId;
	}

	public String getMenuId(){
		return this.menuId;
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
		return "MenuGroup [ id= "+id+
			",gmtCreate= "+gmtCreate+
			",gmtModified= "+gmtModified+
			",menuId= "+menuId+
			",adGroup= "+adGroup+
			",adUserId= "+adUserId+"]";
	}
}
