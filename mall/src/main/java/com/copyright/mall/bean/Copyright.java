package com.copyright.mall.bean;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 版权表
 * @author lijian
 * @date 2020-06-10 13:48
 **/
public class Copyright implements Serializable {

	private static final long serialVersionUID = 4875944118171682313L;

	/**主键**/
	private Long id;

	/****/
	private String copyrightId;

	/**创建时间**/
	private Date gmtCreate;

	/**修改时间**/
	private Date gmtModified;

	/**认证时间**/
	private Date certificationTime;

	/**创作日期**/
	private Date creationTime;

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

	/**商铺id**/
	private Long shopId;

	/**版权人证件**/
	private String certificateOfCopyrightOwner;

	/**登记号**/
	private String registrationNo;

	/**类别**/
	private String category;

	/**作品图片**/
	private String picturesOfWorks;

	/**版权证书**/
	private String copyrightCertificate;

	/**版权人**/
	private String copyrightOwner;



	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setCopyrightId(String copyrightId){
		this.copyrightId = copyrightId;
	}

	public String getCopyrightId(){
		return this.copyrightId;
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

	public void setCertificationTime(Date certificationTime){
		this.certificationTime = certificationTime;
	}

	public Date getCertificationTime(){
		return this.certificationTime;
	}

	public void setCreationTime(Date creationTime){
		this.creationTime = creationTime;
	}

	public Date getCreationTime(){
		return this.creationTime;
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

	public void setShopId(Long shopId){
		this.shopId = shopId;
	}

	public Long getShopId(){
		return this.shopId;
	}

	public void setCertificateOfCopyrightOwner(String certificateOfCopyrightOwner){
		this.certificateOfCopyrightOwner = certificateOfCopyrightOwner;
	}

	public String getCertificateOfCopyrightOwner(){
		return this.certificateOfCopyrightOwner;
	}

	public void setRegistrationNo(String registrationNo){
		this.registrationNo = registrationNo;
	}

	public String getRegistrationNo(){
		return this.registrationNo;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return this.category;
	}

	public void setPicturesOfWorks(String picturesOfWorks){
		this.picturesOfWorks = picturesOfWorks;
	}

	public String getPicturesOfWorks(){
		return this.picturesOfWorks;
	}

	public void setCopyrightCertificate(String copyrightCertificate){
		this.copyrightCertificate = copyrightCertificate;
	}

	public String getCopyrightCertificate(){
		return this.copyrightCertificate;
	}

	public void setCopyrightOwner(String copyrightOwner){
		this.copyrightOwner = copyrightOwner;
	}

	public String getCopyrightOwner(){
		return this.copyrightOwner;
	}

	@Override
	public String toString() {
		return "Copyright [ id= "+id+
			",copyrightId= "+copyrightId+
			",gmtCreate= "+gmtCreate+
			",gmtModified= "+gmtModified+
			",certificationTime= "+certificationTime+
			",creationTime= "+creationTime+
			",chineseName= "+chineseName+
			",name= "+name+
			",auditStatus= "+auditStatus+
			",copyrightLogo= "+copyrightLogo+
			",copyrightImg= "+copyrightImg+
			",uploadUser= "+uploadUser+
			",shopId= "+shopId+
			",certificateOfCopyrightOwner= "+certificateOfCopyrightOwner+
			",registrationNo= "+registrationNo+
			",category= "+category+
			",picturesOfWorks= "+picturesOfWorks+
			",copyrightCertificate= "+copyrightCertificate+
			",copyrightOwner= "+copyrightOwner+"]";
	}
}
