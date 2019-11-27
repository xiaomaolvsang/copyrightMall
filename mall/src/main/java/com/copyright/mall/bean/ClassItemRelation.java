package com.copyright.mall.bean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author lijian
 * @date 2019-11-27 15:25
 **/
public class ClassItemRelation implements Serializable {

	private static final long serialVersionUID = 4317085936074335384L;

	/**主键**/
	private Long id;

	/**商品id**/
	private Long itemId;

	/**分类id**/
	private Long classId;



	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setItemId(Long itemId){
		this.itemId = itemId;
	}

	public Long getItemId(){
		return this.itemId;
	}

	public void setClassId(Long classId){
		this.classId = classId;
	}

	public Long getClassId(){
		return this.classId;
	}

	@Override
	public String toString() {
		return "ClassItemRelation [ id= "+id+
			",itemId= "+itemId+
			",classId= "+classId+"]";
	}
}
