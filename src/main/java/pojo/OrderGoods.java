package pojo;

import java.math.BigDecimal;
import java.util.Date;

public class OrderGoods {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_order_goods.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_order_goods.order_id
     *
     * @mbggenerated
     */
    private Integer orderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_order_goods.goods_id
     *
     * @mbggenerated
     */
    private Integer goodsId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_order_goods.goods_name
     *
     * @mbggenerated
     */
    private String goodsName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_order_goods.goods_sn
     *
     * @mbggenerated
     */
    private String goodsSn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_order_goods.product_id
     *
     * @mbggenerated
     */
    private Integer productId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_order_goods.number
     *
     * @mbggenerated
     */
    private Short number;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_order_goods.price
     *
     * @mbggenerated
     */
    private BigDecimal price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_order_goods.specifications
     *
     * @mbggenerated
     */
    private String specifications;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_order_goods.pic_url
     *
     * @mbggenerated
     */
    private String picUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_order_goods.comment
     *
     * @mbggenerated
     */
    private Integer comment;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_order_goods.add_time
     *
     * @mbggenerated
     */
    private Date addTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_order_goods.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_order_goods.deleted
     *
     * @mbggenerated
     */
    private Boolean deleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_order_goods.id
     *
     * @return the value of librecmall_order_goods.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_order_goods.id
     *
     * @param id the value for librecmall_order_goods.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_order_goods.order_id
     *
     * @return the value of librecmall_order_goods.order_id
     *
     * @mbggenerated
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_order_goods.order_id
     *
     * @param orderId the value for librecmall_order_goods.order_id
     *
     * @mbggenerated
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_order_goods.goods_id
     *
     * @return the value of librecmall_order_goods.goods_id
     *
     * @mbggenerated
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_order_goods.goods_id
     *
     * @param goodsId the value for librecmall_order_goods.goods_id
     *
     * @mbggenerated
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_order_goods.goods_name
     *
     * @return the value of librecmall_order_goods.goods_name
     *
     * @mbggenerated
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_order_goods.goods_name
     *
     * @param goodsName the value for librecmall_order_goods.goods_name
     *
     * @mbggenerated
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_order_goods.goods_sn
     *
     * @return the value of librecmall_order_goods.goods_sn
     *
     * @mbggenerated
     */
    public String getGoodsSn() {
        return goodsSn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_order_goods.goods_sn
     *
     * @param goodsSn the value for librecmall_order_goods.goods_sn
     *
     * @mbggenerated
     */
    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn == null ? null : goodsSn.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_order_goods.product_id
     *
     * @return the value of librecmall_order_goods.product_id
     *
     * @mbggenerated
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_order_goods.product_id
     *
     * @param productId the value for librecmall_order_goods.product_id
     *
     * @mbggenerated
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_order_goods.number
     *
     * @return the value of librecmall_order_goods.number
     *
     * @mbggenerated
     */
    public Short getNumber() {
        return number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_order_goods.number
     *
     * @param number the value for librecmall_order_goods.number
     *
     * @mbggenerated
     */
    public void setNumber(Short number) {
        this.number = number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_order_goods.price
     *
     * @return the value of librecmall_order_goods.price
     *
     * @mbggenerated
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_order_goods.price
     *
     * @param price the value for librecmall_order_goods.price
     *
     * @mbggenerated
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_order_goods.specifications
     *
     * @return the value of librecmall_order_goods.specifications
     *
     * @mbggenerated
     */
    public String getSpecifications() {
        return specifications;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_order_goods.specifications
     *
     * @param specifications the value for librecmall_order_goods.specifications
     *
     * @mbggenerated
     */
    public void setSpecifications(String specifications) {
        this.specifications = specifications == null ? null : specifications.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_order_goods.pic_url
     *
     * @return the value of librecmall_order_goods.pic_url
     *
     * @mbggenerated
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_order_goods.pic_url
     *
     * @param picUrl the value for librecmall_order_goods.pic_url
     *
     * @mbggenerated
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_order_goods.comment
     *
     * @return the value of librecmall_order_goods.comment
     *
     * @mbggenerated
     */
    public Integer getComment() {
        return comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_order_goods.comment
     *
     * @param comment the value for librecmall_order_goods.comment
     *
     * @mbggenerated
     */
    public void setComment(Integer comment) {
        this.comment = comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_order_goods.add_time
     *
     * @return the value of librecmall_order_goods.add_time
     *
     * @mbggenerated
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_order_goods.add_time
     *
     * @param addTime the value for librecmall_order_goods.add_time
     *
     * @mbggenerated
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_order_goods.update_time
     *
     * @return the value of librecmall_order_goods.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_order_goods.update_time
     *
     * @param updateTime the value for librecmall_order_goods.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_order_goods.deleted
     *
     * @return the value of librecmall_order_goods.deleted
     *
     * @mbggenerated
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_order_goods.deleted
     *
     * @param deleted the value for librecmall_order_goods.deleted
     *
     * @mbggenerated
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}