package com.copyright.mall.domain.dto.copyright;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author lijian
 * @date 2020-06-10 13:45
 **/
@Data
public class CertificateDetail implements Serializable {

	private static final long serialVersionUID = 381297420795132295L;

	/****/
	private Integer id;

	/****/
	private String certificateId;

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

	/**授权日期**/
	private Date authorizationDate;

	/**截止日期**/
	private Date closingDate;

	/**授权类型**/
	private String authorizationType;

	/**创建时间**/
	private Date gmtCreate;

	/**修改时间**/
	private Date gmtModified;

	/**认证时间**/
	private Date certificationTime;

	/**创作日期**/
	private Date creationTime;

	/**中文名称**/
	private String chineseName;

	/**名称**/
	private String name;

	/**0-未通过，1-通过**/
	private Integer auditStatus;

	/**版权logo**/
	private String copyrightLogo;

	/**版权图片**/
	private String copyrightImg;

	/**上传用户**/
	private String uploadUser;

	/**商铺id**/
	private Long shopId;

	/**版权人证件**/
	private String certificateOfCopyrightOwner;

	/**登记号**/
	private String registrationNo;

	/**类别**/
	private String category;

	/**作品图片**/
	private String picturesOfWorks;

	/**版权证书**/
	private String copyrightCertificate;

	/**版权人**/
	private String copyrightOwner;

	/** 电话 **/
	private String phone;

	/**
	 * 是否含有子版权
	 */
	private boolean hasSubCopyright;
}
