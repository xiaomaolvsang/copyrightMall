package com.copyright.mall.bean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 结算单
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public class MallOrder implements Serializable {

	private static final long serialVersionUID = 2794321546784287306L;

	/**主键**/
	private Long id;

	/**创建时间**/
	private Date gmtCreate;

	/**修改时间**/
	private Date gmtModified;

	/**主订单id**/
	private String mallOrderId;

	/**商城id**/
	private String mallId;

	/**支付状态**/
	private Integer payStatus;

	/**收货地址**/
	private String deliveryAddress;

	/**收货人姓名**/
	private String deliveryName;

	/**收货人电话**/
	private String phone;

	/**购买人**/
	private String buyer;

	/**价格**/
	private Integer price;



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

	public void setMallOrderId(String mallOrderId){
		this.mallOrderId = mallOrderId;
	}

	public String getMallOrderId(){
		return this.mallOrderId;
	}

	public void setMallId(String mallId){
		this.mallId = mallId;
	}

	public String getMallId(){
		return this.mallId;
	}

	public void setPayStatus(Integer payStatus){
		this.payStatus = payStatus;
	}

	public Integer getPayStatus(){
		return this.payStatus;
	}

	public void setDeliveryAddress(String deliveryAddress){
		this.deliveryAddress = deliveryAddress;
	}

	public String getDeliveryAddress(){
		return this.deliveryAddress;
	}

	public void setDeliveryName(String deliveryName){
		this.deliveryName = deliveryName;
	}

	public String getDeliveryName(){
		return this.deliveryName;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return this.phone;
	}

	public void setBuyer(String buyer){
		this.buyer = buyer;
	}

	public String getBuyer(){
		return this.buyer;
	}

	public void setPrice(Integer price){
		this.price = price;
	}

	public Integer getPrice(){
		return this.price;
	}

	@Override
	public String toString() {
		return "MallOrder [ id= "+id+
			",gmtCreate= "+gmtCreate+
			",gmtModified= "+gmtModified+
			",mallOrderId= "+mallOrderId+
			",mallId= "+mallId+
			",payStatus= "+payStatus+
			",deliveryAddress= "+deliveryAddress+
			",deliveryName= "+deliveryName+
			",phone= "+phone+
			",buyer= "+buyer+
			",price= "+price+"]";
	}
}
