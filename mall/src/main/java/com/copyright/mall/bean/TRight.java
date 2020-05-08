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
public class TRight implements Serializable {

	private static final long serialVersionUID = 236208622992971279L;

	/**主键**/
	private Long id;

	/**父级权限**/
	private Long parentId;

	/**权限名**/
	private String rightName;

	/**权限描述**/
	private String desc;



	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setParentId(Long parentId){
		this.parentId = parentId;
	}

	public Long getParentId(){
		return this.parentId;
	}

	public void setRightName(String rightName){
		this.rightName = rightName;
	}

	public String getRightName(){
		return this.rightName;
	}

	public void setDesc(String desc){
		this.desc = desc;
	}

	public String getDesc(){
		return this.desc;
	}

	@Override
	public String toString() {
		return "TRight [ id= "+id+
			",parentId= "+parentId+
			",rightName= "+rightName+
			",desc= "+desc+"]";
	}
}
