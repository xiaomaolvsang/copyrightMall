package com.copyright.mall.bean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 商铺表
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public class Shop implements Serializable {

	private static final long serialVersionUID = 8013108644888937032L;

	/**主键**/
	private Long id;

	/**创建时间**/
	private Date gmtCreate;

	/**修改时间**/
	private Date gmtModified;

	/**商城主键**/
	private Long mallId;

	/**商铺名称**/
	private String shopName;

	/**商铺logo**/
	private String shopLogo;

	/**店铺类型 0-sale,1-artist**/
	private Integer shopType;

	/**单位名**/
	private String companyName;

	/**pdf**/
	private String certification;



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

	public void setMallId(Long mallId){
		this.mallId = mallId;
	}

	public Long getMallId(){
		return this.mallId;
	}

	public void setShopName(String shopName){
		this.shopName = shopName;
	}

	public String getShopName(){
		return this.shopName;
	}

	public void setShopLogo(String shopLogo){
		this.shopLogo = shopLogo;
	}

	public String getShopLogo(){
		return this.shopLogo;
	}

	public void setShopType(Integer shopType){
		this.shopType = shopType;
	}

	public Integer getShopType(){
		return this.shopType;
	}

	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}

	public String getCompanyName(){
		return this.companyName;
	}

	public void setCertification(String certification){
		this.certification = certification;
	}

	public String getCertification(){
		return this.certification;
	}

	@Override
	public String toString() {
		return "Shop [ id= "+id+
			",gmtCreate= "+gmtCreate+
			",gmtModified= "+gmtModified+
			",mallId= "+mallId+
			",shopName= "+shopName+
			",shopLogo= "+shopLogo+
			",shopType= "+shopType+
			",companyName= "+companyName+
			",certification= "+certification+"]";
	}
}
