package com.copyright.mall.bean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 子订单
 * @author lijian
 * @date 2019-11-27 16:15
 **/
public class ItemOrder implements Serializable {

	private static final long serialVersionUID = 1596382464266134354L;

	/**主键**/
	private Long id;

	/**创建时间**/
	private Date gmtCreate;

	/**修改时间**/
	private Date gmtModified;

	/**子订单号**/
	private String itemOrderId;

	/**商铺订单号**/
	private String shopOrderId;

	/**商品id**/
	private Long itemId;

	/**sku号**/
	private Long skuId;

	/**商品订单状态**/
	private Integer itemOrderStatus;

	/**商品价格**/
	private Integer itemPrice;

	/**商品数量**/
	private Integer itemCount;

	/**商品总价**/
	private Integer itemTotalPrice;

	/**支付价格**/
	private Integer payPrice;

	/**支付时间**/
	private Date payTime;



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

	public void setItemOrderId(String itemOrderId){
		this.itemOrderId = itemOrderId;
	}

	public String getItemOrderId(){
		return this.itemOrderId;
	}

	public void setShopOrderId(String shopOrderId){
		this.shopOrderId = shopOrderId;
	}

	public String getShopOrderId(){
		return this.shopOrderId;
	}

	public void setItemId(Long itemId){
		this.itemId = itemId;
	}

	public Long getItemId(){
		return this.itemId;
	}

	public void setSkuId(Long skuId){
		this.skuId = skuId;
	}

	public Long getSkuId(){
		return this.skuId;
	}

	public void setItemOrderStatus(Integer itemOrderStatus){
		this.itemOrderStatus = itemOrderStatus;
	}

	public Integer getItemOrderStatus(){
		return this.itemOrderStatus;
	}

	public void setItemPrice(Integer itemPrice){
		this.itemPrice = itemPrice;
	}

	public Integer getItemPrice(){
		return this.itemPrice;
	}

	public void setItemCount(Integer itemCount){
		this.itemCount = itemCount;
	}

	public Integer getItemCount(){
		return this.itemCount;
	}

	public void setItemTotalPrice(Integer itemTotalPrice){
		this.itemTotalPrice = itemTotalPrice;
	}

	public Integer getItemTotalPrice(){
		return this.itemTotalPrice;
	}

	public void setPayPrice(Integer payPrice){
		this.payPrice = payPrice;
	}

	public Integer getPayPrice(){
		return this.payPrice;
	}

	public void setPayTime(Date payTime){
		this.payTime = payTime;
	}

	public Date getPayTime(){
		return this.payTime;
	}

	@Override
	public String toString() {
		return "ItemOrder [ id= "+id+
			",gmtCreate= "+gmtCreate+
			",gmtModified= "+gmtModified+
			",itemOrderId= "+itemOrderId+
			",shopOrderId= "+shopOrderId+
			",itemId= "+itemId+
			",skuId= "+skuId+
			",itemOrderStatus= "+itemOrderStatus+
			",itemPrice= "+itemPrice+
			",itemCount= "+itemCount+
			",itemTotalPrice= "+itemTotalPrice+
			",payPrice= "+payPrice+
			",payTime= "+payTime+"]";
	}
}
