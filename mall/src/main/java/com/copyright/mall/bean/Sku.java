package com.copyright.mall.bean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * sku
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public class Sku implements Serializable {

	private static final long serialVersionUID = 2994680742342117943L;

	/**主键**/
	private Long id;

	/**创建时间**/
	private Date gmtCreate;

	/**修改时间**/
	private Date gmtModified;

	/**商品主键**/
	private Long itemId;

	/**商品价格**/
	private Integer price;

	/**尺寸**/
	private String sizeKey;

	/**尺寸值**/
	private String sizeValue;



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

	public void setItemId(Long itemId){
		this.itemId = itemId;
	}

	public Long getItemId(){
		return this.itemId;
	}

	public void setPrice(Integer price){
		this.price = price;
	}

	public Integer getPrice(){
		return this.price;
	}

	public void setSizeKey(String sizeKey){
		this.sizeKey = sizeKey;
	}

	public String getSizeKey(){
		return this.sizeKey;
	}

	public void setSizeValue(String sizeValue){
		this.sizeValue = sizeValue;
	}

	public String getSizeValue(){
		return this.sizeValue;
	}

	@Override
	public String toString() {
		return "Sku [ id= "+id+
			",gmtCreate= "+gmtCreate+
			",gmtModified= "+gmtModified+
			",itemId= "+itemId+
			",price= "+price+
			",sizeKey= "+sizeKey+
			",sizeValue= "+sizeValue+"]";
	}
}
