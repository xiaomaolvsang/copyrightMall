package com.copyright.mall.bean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 商城表
 * @author lijian
 * @date 2019-11-28 14:19
 **/
public class Mall implements Serializable {

	private static final long serialVersionUID = 2128578700123299023L;

	/**主键**/
	private Long id;

	/**创建时间**/
	private Date gmtCreate;

	/**修改时间**/
	private Date gmtModified;

	/**商品名称**/
	private String mallName;

	/**商品logo**/
	private String mallLogo;

	/**单位名**/
	private String companyName;

	/**小程序配置**/
	private String appProperty;

	/**邮箱**/
	private String contactUs;

	/**是否认证（0未认证-1认证）**/
	private Integer isIdentification;



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

	public void setMallName(String mallName){
		this.mallName = mallName;
	}

	public String getMallName(){
		return this.mallName;
	}

	public void setMallLogo(String mallLogo){
		this.mallLogo = mallLogo;
	}

	public String getMallLogo(){
		return this.mallLogo;
	}

	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}

	public String getCompanyName(){
		return this.companyName;
	}

	public void setAppProperty(String appProperty){
		this.appProperty = appProperty;
	}

	public String getAppProperty(){
		return this.appProperty;
	}

	public void setContactUs(String contactUs){
		this.contactUs = contactUs;
	}

	public String getContactUs(){
		return this.contactUs;
	}

	public void setIsIdentification(Integer isIdentification){
		this.isIdentification = isIdentification;
	}

	public Integer getIsIdentification(){
		return this.isIdentification;
	}

	@Override
	public String toString() {
		return "Mall [ id= "+id+
			",gmtCreate= "+gmtCreate+
			",gmtModified= "+gmtModified+
			",mallName= "+mallName+
			",mallLogo= "+mallLogo+
			",companyName= "+companyName+
			",appProperty= "+appProperty+
			",contactUs= "+contactUs+
			",isIdentification= "+isIdentification+"]";
	}
}
