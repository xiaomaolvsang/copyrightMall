package com.copyright.mall.bean;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 
 * 
 * @author lijian
 * @date 2020-06-10 17:27
 **/
@Data
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

	/**
	 * 查询条件
	 */
	private String phone;

	/**查询条件版权人**/
	private String copyrightOwner;

	/**
	 * 查询条件
	 */
	private String chineseName;

	/**查询条件 状态集 **/
	private List<Integer> statuses;
}
