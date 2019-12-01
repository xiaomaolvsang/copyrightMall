package com.copyright.mall.bean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author lijian
 * @date 2019-11-30 10:36
 **/
public class UserAddress implements Serializable {

	private static final long serialVersionUID = 2349033212350820092L;

	/**主键**/
	private Long id;

	/**user_id**/
	private Long userId;

	/**省**/
	private String province;

	/**市**/
	private String city;

	/**区**/
	private String area;

	/**街道**/
	private String street;

	/**详情**/
	private String detail;

	/**收货人姓名**/
	private String consigneeName;

	/**收货人电话**/
	private String consigneePhone;

	/**删除字段**/
	private Integer delFlag;

	/**创建时间**/
	private Date createTime;

	/**更新时间**/
	private Date updateTime;



	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setUserId(Long userId){
		this.userId = userId;
	}

	public Long getUserId(){
		return this.userId;
	}

	public void setProvince(String province){
		this.province = province;
	}

	public String getProvince(){
		return this.province;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return this.city;
	}

	public void setArea(String area){
		this.area = area;
	}

	public String getArea(){
		return this.area;
	}

	public void setStreet(String street){
		this.street = street;
	}

	public String getStreet(){
		return this.street;
	}

	public void setDetail(String detail){
		this.detail = detail;
	}

	public String getDetail(){
		return this.detail;
	}

	public void setConsigneeName(String consigneeName){
		this.consigneeName = consigneeName;
	}

	public String getConsigneeName(){
		return this.consigneeName;
	}

	public void setConsigneePhone(String consigneePhone){
		this.consigneePhone = consigneePhone;
	}

	public String getConsigneePhone(){
		return this.consigneePhone;
	}

	public void setDelFlag(Integer delFlag){
		this.delFlag = delFlag;
	}

	public Integer getDelFlag(){
		return this.delFlag;
	}

	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}

	public Date getUpdateTime(){
		return this.updateTime;
	}

	@Override
	public String toString() {
		return "UserAddress [ id= "+id+
			",userId= "+userId+
			",province= "+province+
			",city= "+city+
			",area= "+area+
			",street= "+street+
			",detail= "+detail+
			",consigneeName= "+consigneeName+
			",consigneePhone= "+consigneePhone+
			",delFlag= "+delFlag+
			",createTime= "+createTime+
			",updateTime= "+updateTime+"]";
	}
}
