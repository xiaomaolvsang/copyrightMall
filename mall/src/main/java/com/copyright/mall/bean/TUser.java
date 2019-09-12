package com.copyright.mall.bean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author lijian
 * @date 2019-09-12 17:09
 **/
public class TUser implements Serializable {

	private static final long serialVersionUID = 6227821390686342808L;

	/****/
	private Integer userId;

	/****/
	private String userRealname;

	/****/
	private String userSex;

	/****/
	private String userTel;

	/****/
	private String userAddress;

	/****/
	private String userName;

	/****/
	private String userPw;

	/****/
	private String userDel;



	public void setUserId(Integer userId){
		this.userId = userId;
	}

	public Integer getUserId(){
		return this.userId;
	}

	public void setUserRealname(String userRealname){
		this.userRealname = userRealname;
	}

	public String getUserRealname(){
		return this.userRealname;
	}

	public void setUserSex(String userSex){
		this.userSex = userSex;
	}

	public String getUserSex(){
		return this.userSex;
	}

	public void setUserTel(String userTel){
		this.userTel = userTel;
	}

	public String getUserTel(){
		return this.userTel;
	}

	public void setUserAddress(String userAddress){
		this.userAddress = userAddress;
	}

	public String getUserAddress(){
		return this.userAddress;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return this.userName;
	}

	public void setUserPw(String userPw){
		this.userPw = userPw;
	}

	public String getUserPw(){
		return this.userPw;
	}

	public void setUserDel(String userDel){
		this.userDel = userDel;
	}

	public String getUserDel(){
		return this.userDel;
	}

	@Override
	public String toString() {
		return "TUser [ userId= "+userId+
			",userRealname= "+userRealname+
			",userSex= "+userSex+
			",userTel= "+userTel+
			",userAddress= "+userAddress+
			",userName= "+userName+
			",userPw= "+userPw+
			",userDel= "+userDel+"]";
	}
}
