package com.copyright.mall.bean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author lijian
 * @date 2020-06-13 12:26
 **/
public class LikeOpusRelation implements Serializable {

	private static final long serialVersionUID = 4495629170022706857L;

	/**主键**/
	private Long id;

	/**创建时间**/
	private Date gmtCreate;

	/**修改时间**/
	private Date gmtModified;

	/**点赞作品id**/
	private Long opusId;

	/**点赞人id**/
	private Long userId;



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

	public void setOpusId(Long opusId){
		this.opusId = opusId;
	}

	public Long getOpusId(){
		return this.opusId;
	}

	public void setUserId(Long userId){
		this.userId = userId;
	}

	public Long getUserId(){
		return this.userId;
	}

	@Override
	public String toString() {
		return "LikeOpusRelation [ id= "+id+
			",gmtCreate= "+gmtCreate+
			",gmtModified= "+gmtModified+
			",opusId= "+opusId+
			",userId= "+userId+"]";
	}
}
