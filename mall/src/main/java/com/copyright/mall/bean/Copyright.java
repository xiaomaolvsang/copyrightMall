package com.copyright.mall.bean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 版权表
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public class Copyright implements Serializable {

	private static final long serialVersionUID = 6110868543068216552L;

	/**主键**/
	private Long id;

	/**创建时间**/
	private Date gmtCreate;

	/**修改时间**/
	private Date gmtModified;

	/**中文名称**/
	private String chineseName;

	/**名称**/
	private String name;

	/**0-未通过，1-通过**/
	private Integer auditStatus;

	/**版权logo**/
	private String copyrightLogo;

	/**版权图片**/
	private String copyrightImg;

	/**上传用户**/
	private String uploadUser;



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

	public void setChineseName(String chineseName){
		this.chineseName = chineseName;
	}

	public String getChineseName(){
		return this.chineseName;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setAuditStatus(Integer auditStatus){
		this.auditStatus = auditStatus;
	}

	public Integer getAuditStatus(){
		return this.auditStatus;
	}

	public void setCopyrightLogo(String copyrightLogo){
		this.copyrightLogo = copyrightLogo;
	}

	public String getCopyrightLogo(){
		return this.copyrightLogo;
	}

	public void setCopyrightImg(String copyrightImg){
		this.copyrightImg = copyrightImg;
	}

	public String getCopyrightImg(){
		return this.copyrightImg;
	}

	public void setUploadUser(String uploadUser){
		this.uploadUser = uploadUser;
	}

	public String getUploadUser(){
		return this.uploadUser;
	}

	@Override
	public String toString() {
		return "Copyright [ id= "+id+
			",gmtCreate= "+gmtCreate+
			",gmtModified= "+gmtModified+
			",chineseName= "+chineseName+
			",name= "+name+
			",auditStatus= "+auditStatus+
			",copyrightLogo= "+copyrightLogo+
			",copyrightImg= "+copyrightImg+
			",uploadUser= "+uploadUser+"]";
	}
}
