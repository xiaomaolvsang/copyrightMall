package com.copyright.mall.bean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 菜单表
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public class Menu implements Serializable {

	private static final long serialVersionUID = 7852378127444391497L;

	/**主键**/
	private Long id;

	/**创建时间**/
	private Date gmtCreate;

	/**修改时间**/
	private Date gmtModified;

	/**菜单id**/
	private String menuId;

	/**菜单名称**/
	private String menuName;

	/**菜单分类**/
	private String menuClass;

	/**父级菜单**/
	private String upperMenu;



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

	public void setMenuName(String menuName){
		this.menuName = menuName;
	}

	public String getMenuName(){
		return this.menuName;
	}

	public void setMenuClass(String menuClass){
		this.menuClass = menuClass;
	}

	public String getMenuClass(){
		return this.menuClass;
	}

	public void setUpperMenu(String upperMenu){
		this.upperMenu = upperMenu;
	}

	public String getUpperMenu(){
		return this.upperMenu;
	}

	@Override
	public String toString() {
		return "Menu [ id= "+id+
			",gmtCreate= "+gmtCreate+
			",gmtModified= "+gmtModified+
			",menuId= "+menuId+
			",menuName= "+menuName+
			",menuClass= "+menuClass+
			",upperMenu= "+upperMenu+"]";
	}
}
