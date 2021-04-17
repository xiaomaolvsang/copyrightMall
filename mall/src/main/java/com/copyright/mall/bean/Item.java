package com.copyright.mall.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 商品表
 *
 * @author lijian
 * @date 2019-11-27 17:03
 **/
public class Item implements Serializable {

    private static final long serialVersionUID = 703528804564158937L;

    /**
     * 主键
     **/
    private Long id;

    /**
     * 创建时间
     **/
    private Date gmtCreate;

    /**
     * 修改时间
     **/
    private Date gmtModified;

    /**
     * 商铺主键
     **/
    private Long shopId;

    /**
     * 商品code
     **/
    private String barcode;

    /**
     * 品类
     **/
    private String itemClass;

    /****/
    private String relatedCopyright;

    /**
     * 商品状态 1上架 0下架
     **/
    private Integer itemStatus;

    /**
     * 商品名称
     **/
    private String itemTitle;

    /****/
    private String ad;

    /**
     * 商品图片
     **/
    private String titleImg;

    /**
     * 商品描述图片
     **/
    private String contentImg;

    /**
     * 艺术类别
     **/
    private String artCategory;

    /**
     * 商品价格
     **/
    private Integer price;

    /**
     * 划线价
     */
    private Integer underlinedPrice;

    /**
     * 库存
     */
    private Integer inventory;

    /**
     * 尺寸
     **/
    private String sizeKey;

    /**
     * 尺寸值
     **/
    private String sizeValue;

    private String companyName;

    private String className;

    private String shopType;
    /**
     * 一级分类为0
     **/
    private Integer itemType;

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getSizeKey() {
        return sizeKey;
    }

    public void setSizeKey(String sizeKey) {
        this.sizeKey = sizeKey;
    }

    public String getSizeValue() {
        return sizeValue;
    }

    public void setSizeValue(String sizeValue) {
        this.sizeValue = sizeValue;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtCreate() {
        return this.gmtCreate;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtModified() {
        return this.gmtModified;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getShopId() {
        return this.shopId;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setItemClass(String itemClass) {
        this.itemClass = itemClass;
    }

    public String getItemClass() {
        return this.itemClass;
    }

    public void setRelatedCopyright(String relatedCopyright) {
        this.relatedCopyright = relatedCopyright;
    }

    public String getRelatedCopyright() {
        return this.relatedCopyright;
    }

    public void setItemStatus(Integer itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Integer getItemStatus() {
        return this.itemStatus;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemTitle() {
        return this.itemTitle;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getAd() {
        return this.ad;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public String getTitleImg() {
        return this.titleImg;
    }

    public void setContentImg(String contentImg) {
        this.contentImg = contentImg;
    }

    public String getContentImg() {
        return this.contentImg;
    }

    public void setArtCategory(String artCategory) {
        this.artCategory = artCategory;
    }

    public String getArtCategory() {
        return this.artCategory;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public Integer getUnderlinedPrice() {
        return underlinedPrice;
    }

    public void setUnderlinedPrice(Integer underlinedPrice) {
        this.underlinedPrice = underlinedPrice;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Item [ id= " + id +
                ",gmtCreate= " + gmtCreate +
                ",gmtModified= " + gmtModified +
                ",shopId= " + shopId +
                ",barcode= " + barcode +
                ",itemClass= " + itemClass +
                ",relatedCopyright= " + relatedCopyright +
                ",itemStatus= " + itemStatus +
                ",itemTitle= " + itemTitle +
                ",ad= " + ad +
                ",titleImg= " + titleImg +
                ",contentImg= " + contentImg +
                ",artCategory= " + artCategory + "]";
    }
}
