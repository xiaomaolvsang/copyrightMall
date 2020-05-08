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
public class UserTRightRelation implements Serializable {

	private static final long serialVersionUID = 1528302212907627295L;

	/**主键**/
	private Long id;

	/**用户id**/
	private Long userId;

	/**权限id**/
	private Long rightId;



	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setUserId(Long userId){
		this.userId = userId;
	}

	public Long getUserId(){
		return this.userId;
	}

	public void setRightId(Long rightId){
		this.rightId = rightId;
	}

	public Long getRightId(){
		return this.rightId;
	}

	@Override
	public String toString() {
		return "UserTRightRelation [ id= "+id+
			",userId= "+userId+
			",rightId= "+rightId+"]";
	}
}
