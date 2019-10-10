package com.copyright.mall.bean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 订单流水
 * @author lijian
 * @date 2019-10-10 16:30
 **/
public class OrderOperateLog implements Serializable {

	private static final long serialVersionUID = 8147047748363027017L;

	/**主键**/
	private Long id;

	/**创建时间**/
	private Date gmtCreate;

	/**修改时间**/
	private Date gmtModified;

	/**订单状态**/
	private Integer orderType;

	/**订单号**/
	private String orderId;

	/**更改状态**/
	private Integer changeType;

	/**更改log**/
	private String changeLogDetail;

	/**修改人**/
	private String changeUser;



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

	public void setOrderType(Integer orderType){
		this.orderType = orderType;
	}

	public Integer getOrderType(){
		return this.orderType;
	}

	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getOrderId(){
		return this.orderId;
	}

	public void setChangeType(Integer changeType){
		this.changeType = changeType;
	}

	public Integer getChangeType(){
		return this.changeType;
	}

	public void setChangeLogDetail(String changeLogDetail){
		this.changeLogDetail = changeLogDetail;
	}

	public String getChangeLogDetail(){
		return this.changeLogDetail;
	}

	public void setChangeUser(String changeUser){
		this.changeUser = changeUser;
	}

	public String getChangeUser(){
		return this.changeUser;
	}

	@Override
	public String toString() {
		return "OrderOperateLog [ id= "+id+
			",gmtCreate= "+gmtCreate+
			",gmtModified= "+gmtModified+
			",orderType= "+orderType+
			",orderId= "+orderId+
			",changeType= "+changeType+
			",changeLogDetail= "+changeLogDetail+
			",changeUser= "+changeUser+"]";
	}
}
