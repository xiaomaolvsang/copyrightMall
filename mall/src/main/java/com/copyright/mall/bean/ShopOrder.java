package com.copyright.mall.bean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 商铺订单
 * @author lijian
 * @date 2019-11-27 15:25
 **/
public class ShopOrder implements Serializable {

	private static final long serialVersionUID = 1278374743828451883L;

	/**主键**/
	private Long id;

	/**创建时间**/
	private Date gmtCreate;

	/**修改时间**/
	private Date gmtModified;

	/**主订单号**/
	private String mallOrderId;

	/**商城订单号**/
	private String shopOrderId;

	/**订单状态**/
	private Integer orderType;

	/**商铺id**/
	private Long shopId;

	/**价格**/
	private Integer price;

	/**运单号**/
	private String deliveryOrderId;

	/**物流名称**/
	private String delliveryCompanyName;

	/**订单创建时间**/
	private Date orderCreateTime;

	/**支付价格**/
	private Integer payPrice;



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

	public void setShopOrderId(String shopOrderId){
		this.shopOrderId = shopOrderId;
	}

	public String getShopOrderId(){
		return this.shopOrderId;
	}

	public void setOrderType(Integer orderType){
		this.orderType = orderType;
	}

	public Integer getOrderType(){
		return this.orderType;
	}

	public void setShopId(Long shopId){
		this.shopId = shopId;
	}

	public Long getShopId(){
		return this.shopId;
	}

	public void setPrice(Integer price){
		this.price = price;
	}

	public Integer getPrice(){
		return this.price;
	}

	public void setDeliveryOrderId(String deliveryOrderId){
		this.deliveryOrderId = deliveryOrderId;
	}

	public String getDeliveryOrderId(){
		return this.deliveryOrderId;
	}

	public void setDelliveryCompanyName(String delliveryCompanyName){
		this.delliveryCompanyName = delliveryCompanyName;
	}

	public String getDelliveryCompanyName(){
		return this.delliveryCompanyName;
	}

	public void setOrderCreateTime(Date orderCreateTime){
		this.orderCreateTime = orderCreateTime;
	}

	public Date getOrderCreateTime(){
		return this.orderCreateTime;
	}

	public void setPayPrice(Integer payPrice){
		this.payPrice = payPrice;
	}

	public Integer getPayPrice(){
		return this.payPrice;
	}

	@Override
	public String toString() {
		return "ShopOrder [ id= "+id+
			",gmtCreate= "+gmtCreate+
			",gmtModified= "+gmtModified+
			",mallOrderId= "+mallOrderId+
			",shopOrderId= "+shopOrderId+
			",orderType= "+orderType+
			",shopId= "+shopId+
			",price= "+price+
			",deliveryOrderId= "+deliveryOrderId+
			",delliveryCompanyName= "+delliveryCompanyName+
			",orderCreateTime= "+orderCreateTime+
			",payPrice= "+payPrice+"]";
	}
}
