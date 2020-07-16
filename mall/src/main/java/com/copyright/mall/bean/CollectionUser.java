package com.copyright.mall.bean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author lijian
 * @date 2020-07-15 23:18
 **/
public class CollectionUser implements Serializable {

	private static final long serialVersionUID = 5367525709607777694L;

	/**主键**/
	private Long id;

	/**用户名称**/
	private Long userId;

	/**作品名称**/
	private Long opusId;



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

	public void setOpusId(Long opusId){
		this.opusId = opusId;
	}

	public Long getOpusId(){
		return this.opusId;
	}

	@Override
	public String toString() {
		return "CollectionUser [ id= "+id+
			",userId= "+userId+
			",opusId= "+opusId+"]";
	}
}
