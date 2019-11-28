package com.copyright.mall.bean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author lijian
 * @date 2019-11-28 13:57
 **/
public class ArtistOpus implements Serializable {

	private static final long serialVersionUID = 1651493172263665709L;

	/**主键**/
	private Long id;

	/**图片**/
	private String image;

	/**artId**/
	private Long itemId;

	/**作品名称**/
	private String name;



	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return this.image;
	}

	public void setItemId(Long itemId){
		this.itemId = itemId;
	}

	public Long getItemId(){
		return this.itemId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	@Override
	public String toString() {
		return "ArtistOpus [ id= "+id+
			",image= "+image+
			",itemId= "+itemId+
			",name= "+name+"]";
	}
}
