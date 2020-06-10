package com.copyright.mall.bean;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author lijian
 * @date 2020-06-10 17:27
 **/
public class Certificate implements Serializable {

	private static final long serialVersionUID = 9051489377499395264L;

	/****/
	private Integer id;

	/****/
	private String certificateId;

	/**父周授权ID**/
	private String pcertificateId;

	/**版权ID**/
	private String copyrightId;

	/**状态**/
	private Integer cerificateStatus;

	/**授权人**/
	private String authorizer;

	/**授权人名**/
	private String authorizerName;

	/**时间线**/
	private String timeLine;

	/**被授权人**/
	private String authorizedPerson;

	/**被授权人名**/
	private String authorizedPersionName;

	/**证书类型  0 主 1 子**/
	private Integer type;

	/**授权日志**/
	private Date authorizationDate;

	/**截止日期**/
	private Date closingDate;

	/**授权类型**/
	private String authorizationType;



	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setCertificateId(String certificateId){
		this.certificateId = certificateId;
	}

	public String getCertificateId(){
		return this.certificateId;
	}

	public void setPcertificateId(String pcertificateId){
		this.pcertificateId = pcertificateId;
	}

	public String getPcertificateId(){
		return this.pcertificateId;
	}

	public void setCopyrightId(String copyrightId){
		this.copyrightId = copyrightId;
	}

	public String getCopyrightId(){
		return this.copyrightId;
	}

	public void setCerificateStatus(Integer cerificateStatus){
		this.cerificateStatus = cerificateStatus;
	}

	public Integer getCerificateStatus(){
		return this.cerificateStatus;
	}

	public void setAuthorizer(String authorizer){
		this.authorizer = authorizer;
	}

	public String getAuthorizer(){
		return this.authorizer;
	}

	public void setAuthorizerName(String authorizerName){
		this.authorizerName = authorizerName;
	}

	public String getAuthorizerName(){
		return this.authorizerName;
	}

	public void setTimeLine(String timeLine){
		this.timeLine = timeLine;
	}

	public String getTimeLine(){
		return this.timeLine;
	}

	public void setAuthorizedPerson(String authorizedPerson){
		this.authorizedPerson = authorizedPerson;
	}

	public String getAuthorizedPerson(){
		return this.authorizedPerson;
	}

	public void setAuthorizedPersionName(String authorizedPersionName){
		this.authorizedPersionName = authorizedPersionName;
	}

	public String getAuthorizedPersionName(){
		return this.authorizedPersionName;
	}

	public void setType(Integer type){
		this.type = type;
	}

	public Integer getType(){
		return this.type;
	}

	public void setAuthorizationDate(Date authorizationDate){
		this.authorizationDate = authorizationDate;
	}

	public Date getAuthorizationDate(){
		return this.authorizationDate;
	}

	public void setClosingDate(Date closingDate){
		this.closingDate = closingDate;
	}

	public Date getClosingDate(){
		return this.closingDate;
	}

	public void setAuthorizationType(String authorizationType){
		this.authorizationType = authorizationType;
	}

	public String getAuthorizationType(){
		return this.authorizationType;
	}

	@Override
	public String toString() {
		return "Certificate [ id= "+id+
			",certificateId= "+certificateId+
			",pcertificateId= "+pcertificateId+
			",copyrightId= "+copyrightId+
			",cerificateStatus= "+cerificateStatus+
			",authorizer= "+authorizer+
			",authorizerName= "+authorizerName+
			",timeLine= "+timeLine+
			",authorizedPerson= "+authorizedPerson+
			",authorizedPersionName= "+authorizedPersionName+
			",type= "+type+
			",authorizationDate= "+authorizationDate+
			",closingDate= "+closingDate+
			",authorizationType= "+authorizationType+"]";
	}
}
