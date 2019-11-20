package com.copyright.mall.bean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author lijian
 * @date 2019-11-20 15:16
 **/
public class Classification implements Serializable {

	private static final long serialVersionUID = 1056199217804519990L;

	/**主键**/
	private Long id;

	/**分类名称**/
	private String className;

	/**一二级分类 一级分类为0**/
	private Long upperId;

	/**商城id**/
	private Long mallId;



	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setClassName(String className){
		this.className = className;
	}

	public String getClassName(){
		return this.className;
	}

	public void setUpperId(Long upperId){
		this.upperId = upperId;
	}

	public Long getUpperId(){
		return this.upperId;
	}

	public void setMallId(Long mallId){
		this.mallId = mallId;
	}

	public Long getMallId(){
		return this.mallId;
	}

	@Override
	public String toString() {
		return "Classification [ id= "+id+
			",className= "+className+
			",upperId= "+upperId+
			",mallId= "+mallId+"]";
	}
}
