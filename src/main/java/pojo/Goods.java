package pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Goods {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_goods.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_goods.goods_sn
     *
     * @mbggenerated
     */
    private String goodsSn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_goods.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_goods.category_id
     *
     * @mbggenerated
     */
    private Integer categoryId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_goods.brand_id
     *
     * @mbggenerated
     */
    private Integer brandId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_goods.gallery
     *
     * @mbggenerated
     */
    private String gallery;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_goods.keywords
     *
     * @mbggenerated
     */
    private String keywords;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_goods.brief
     *
     * @mbggenerated
     */
    private String brief;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_goods.is_on_sale
     *
     * @mbggenerated
     */
    private Boolean isOnSale;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_goods.sort_order
     *
     * @mbggenerated
     */
    private Short sortOrder;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_goods.pic_url
     *
     * @mbggenerated
     */
    private String picUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_goods.share_url
     *
     * @mbggenerated
     */
    private String shareUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_goods.is_new
     *
     * @mbggenerated
     */
    private Boolean isNew;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_goods.is_hot
     *
     * @mbggenerated
     */
    private Boolean isHot;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_goods.unit
     *
     * @mbggenerated
     */
    private String unit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_goods.counter_price
     *
     * @mbggenerated
     */
    private BigDecimal counterPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_goods.retail_price
     *
     * @mbggenerated
     */
    private BigDecimal retailPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_goods.add_time
     *
     * @mbggenerated
     */
    private Date addTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_goods.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_goods.deleted
     *
     * @mbggenerated
     */
    private Boolean deleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_goods.detail
     *
     * @mbggenerated
     */
    private String detail;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_goods.id
     *
     * @return the value of librecmall_goods.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_goods.id
     *
     * @param id the value for librecmall_goods.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_goods.goods_sn
     *
     * @return the value of librecmall_goods.goods_sn
     *
     * @mbggenerated
     */
    public String getGoodsSn() {
        return goodsSn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_goods.goods_sn
     *
     * @param goodsSn the value for librecmall_goods.goods_sn
     *
     * @mbggenerated
     */
    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn == null ? null : goodsSn.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_goods.name
     *
     * @return the value of librecmall_goods.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_goods.name
     *
     * @param name the value for librecmall_goods.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_goods.category_id
     *
     * @return the value of librecmall_goods.category_id
     *
     * @mbggenerated
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_goods.category_id
     *
     * @param categoryId the value for librecmall_goods.category_id
     *
     * @mbggenerated
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_goods.brand_id
     *
     * @return the value of librecmall_goods.brand_id
     *
     * @mbggenerated
     */
    public Integer getBrandId() {
        return brandId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_goods.brand_id
     *
     * @param brandId the value for librecmall_goods.brand_id
     *
     * @mbggenerated
     */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_goods.gallery
     *
     * @return the value of librecmall_goods.gallery
     *
     * @mbggenerated
     */
    public String getGallery() {
        return gallery;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_goods.gallery
     *
     * @param gallery the value for librecmall_goods.gallery
     *
     * @mbggenerated
     */
    public void setGallery(String gallery) {
        this.gallery = gallery == null ? null : gallery.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_goods.keywords
     *
     * @return the value of librecmall_goods.keywords
     *
     * @mbggenerated
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_goods.keywords
     *
     * @param keywords the value for librecmall_goods.keywords
     *
     * @mbggenerated
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_goods.brief
     *
     * @return the value of librecmall_goods.brief
     *
     * @mbggenerated
     */
    public String getBrief() {
        return brief;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_goods.brief
     *
     * @param brief the value for librecmall_goods.brief
     *
     * @mbggenerated
     */
    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_goods.is_on_sale
     *
     * @return the value of librecmall_goods.is_on_sale
     *
     * @mbggenerated
     */
    public Boolean getIsOnSale() {
        return isOnSale;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_goods.is_on_sale
     *
     * @param isOnSale the value for librecmall_goods.is_on_sale
     *
     * @mbggenerated
     */
    public void setIsOnSale(Boolean isOnSale) {
        this.isOnSale = isOnSale;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_goods.sort_order
     *
     * @return the value of librecmall_goods.sort_order
     *
     * @mbggenerated
     */
    public Short getSortOrder() {
        return sortOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_goods.sort_order
     *
     * @param sortOrder the value for librecmall_goods.sort_order
     *
     * @mbggenerated
     */
    public void setSortOrder(Short sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_goods.pic_url
     *
     * @return the value of librecmall_goods.pic_url
     *
     * @mbggenerated
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_goods.pic_url
     *
     * @param picUrl the value for librecmall_goods.pic_url
     *
     * @mbggenerated
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_goods.share_url
     *
     * @return the value of librecmall_goods.share_url
     *
     * @mbggenerated
     */
    public String getShareUrl() {
        return shareUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_goods.share_url
     *
     * @param shareUrl the value for librecmall_goods.share_url
     *
     * @mbggenerated
     */
    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl == null ? null : shareUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_goods.is_new
     *
     * @return the value of librecmall_goods.is_new
     *
     * @mbggenerated
     */
    public Boolean getIsNew() {
        return isNew;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_goods.is_new
     *
     * @param isNew the value for librecmall_goods.is_new
     *
     * @mbggenerated
     */
    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_goods.is_hot
     *
     * @return the value of librecmall_goods.is_hot
     *
     * @mbggenerated
     */
    public Boolean getIsHot() {
        return isHot;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_goods.is_hot
     *
     * @param isHot the value for librecmall_goods.is_hot
     *
     * @mbggenerated
     */
    public void setIsHot(Boolean isHot) {
        this.isHot = isHot;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_goods.unit
     *
     * @return the value of librecmall_goods.unit
     *
     * @mbggenerated
     */
    public String getUnit() {
        return unit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_goods.unit
     *
     * @param unit the value for librecmall_goods.unit
     *
     * @mbggenerated
     */
    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_goods.counter_price
     *
     * @return the value of librecmall_goods.counter_price
     *
     * @mbggenerated
     */
    public BigDecimal getCounterPrice() {
        return counterPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_goods.counter_price
     *
     * @param counterPrice the value for librecmall_goods.counter_price
     *
     * @mbggenerated
     */
    public void setCounterPrice(BigDecimal counterPrice) {
        this.counterPrice = counterPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_goods.retail_price
     *
     * @return the value of librecmall_goods.retail_price
     *
     * @mbggenerated
     */
    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_goods.retail_price
     *
     * @param retailPrice the value for librecmall_goods.retail_price
     *
     * @mbggenerated
     */
    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_goods.add_time
     *
     * @return the value of librecmall_goods.add_time
     *
     * @mbggenerated
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_goods.add_time
     *
     * @param addTime the value for librecmall_goods.add_time
     *
     * @mbggenerated
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_goods.update_time
     *
     * @return the value of librecmall_goods.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_goods.update_time
     *
     * @param updateTime the value for librecmall_goods.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_goods.deleted
     *
     * @return the value of librecmall_goods.deleted
     *
     * @mbggenerated
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_goods.deleted
     *
     * @param deleted the value for librecmall_goods.deleted
     *
     * @mbggenerated
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_goods.detail
     *
     * @return the value of librecmall_goods.detail
     *
     * @mbggenerated
     */
    public String getDetail() {
        return detail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_goods.detail
     *
     * @param detail the value for librecmall_goods.detail
     *
     * @mbggenerated
     */
    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}