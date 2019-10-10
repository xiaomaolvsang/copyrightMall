package com.copyright.mall.bean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 购物车
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public class Cart implements Serializable {

	private static final long serialVersionUID = 8550814964367536356L;

	/**主键**/
	private Long id;

	/**创建时间**/
	private Date gmtCreate;

	/**修改时间**/
	private Date gmtModified;

	/**商城id**/
	private Long mallId;

	/**商铺id**/
	private Long shopId;

	/**用户id**/
	private Long userId;

	/**购物车状态**/
	private Integer cartStatus;

	/**商品id**/
	private Long itemId;

	/**skuid**/
	private Long skuId;

	/**数量**/
	private Integer count;

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

	public void setMallId(Long mallId){
		this.mallId = mallId;
	}

	public Long getMallId(){
		return this.mallId;
	}

	public void setShopId(Long shopId){
		this.shopId = shopId;
	}

	public Long getShopId(){
		return this.shopId;
	}

	public void setUserId(Long userId){
		this.userId = userId;
	}

	public Long getUserId(){
		return this.userId;
	}

	public void setCartStatus(Integer cartStatus){
		this.cartStatus = cartStatus;
	}

	public Integer getCartStatus(){
		return this.cartStatus;
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

	public void setCount(Integer count){
		this.count = count;
	}

	public Integer getCount(){
		return this.count;
	}

	public void setPrice(Integer price){
		this.price = price;
	}

	public Integer getPrice(){
		return this.price;
	}

	@Override
	public String toString() {
		return "Cart [ id= "+id+
			",gmtCreate= "+gmtCreate+
			",gmtModified= "+gmtModified+
			",mallId= "+mallId+
			",shopId= "+shopId+
			",userId= "+userId+
			",cartStatus= "+cartStatus+
			",itemId= "+itemId+
			",skuId= "+skuId+
			",count= "+count+
			",price= "+price+"]";
	}
}
