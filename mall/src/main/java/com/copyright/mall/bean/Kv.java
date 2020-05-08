package com.copyright.mall.bean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author lijian
 * @date 2020-05-08 14:36
 **/
public class Kv implements Serializable {

	private static final long serialVersionUID = 7645219979413209509L;

	/****/
	private Integer id;

	/****/
	private String blobTitle;

	/****/
	private String content;

	/****/
	private Date startTime;

	/****/
	private Date updateTime;

	/****/
	private String creator;



	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setBlobTitle(String blobTitle){
		this.blobTitle = blobTitle;
	}

	public String getBlobTitle(){
		return this.blobTitle;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return this.content;
	}

	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}

	public Date getStartTime(){
		return this.startTime;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}

	public Date getUpdateTime(){
		return this.updateTime;
	}

	public void setCreator(String creator){
		this.creator = creator;
	}

	public String getCreator(){
		return this.creator;
	}

	@Override
	public String toString() {
		return "Kv [ id= "+id+
			",blobTitle= "+blobTitle+
			",content= "+content+
			",startTime= "+startTime+
			",updateTime= "+updateTime+
			",creator= "+creator+"]";
	}
}
