package com.copyright.mall.bean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author lijian
 * @date 2019-11-28 15:36
 **/
public class ArtistOpus implements Serializable {

	private static final long serialVersionUID = 7341178059108483593L;

	/**主键**/
	private Long id;

	/**图片**/
	private String image;

	/**artId**/
	private Long itemId;

	/**作品名称**/
	private String name;

	/**作品标题**/
	private String title;

	/**作品描述**/
	private String desc;

	/**图片集**/
	private String imgs;



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

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return this.title;
	}

	public void setDesc(String desc){
		this.desc = desc;
	}

	public String getDesc(){
		return this.desc;
	}

	public void setImgs(String imgs){
		this.imgs = imgs;
	}

	public String getImgs(){
		return this.imgs;
	}

	@Override
	public String toString() {
		return "ArtistOpus [ id= "+id+
			",image= "+image+
			",itemId= "+itemId+
			",name= "+name+
			",title= "+title+
			",desc= "+desc+
			",imgs= "+imgs+"]";
	}
}
