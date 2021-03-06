package pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Coupon {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_coupon.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_coupon.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_coupon.desc
     *
     * @mbggenerated
     */
    private String desc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_coupon.tag
     *
     * @mbggenerated
     */
    private String tag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_coupon.total
     *
     * @mbggenerated
     */
    private Integer total;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_coupon.discount
     *
     * @mbggenerated
     */
    private BigDecimal discount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_coupon.min
     *
     * @mbggenerated
     */
    private BigDecimal min;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_coupon.limit
     *
     * @mbggenerated
     */
    private Short limit;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_coupon.type
     *
     * @mbggenerated
     */
    private Short type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_coupon.status
     *
     * @mbggenerated
     */
    private Short status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_coupon.goods_type
     *
     * @mbggenerated
     */
    private Short goodsType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_coupon.goods_value
     *
     * @mbggenerated
     */
    private String goodsValue;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_coupon.code
     *
     * @mbggenerated
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_coupon.time_type
     *
     * @mbggenerated
     */
    private Short timeType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_coupon.days
     *
     * @mbggenerated
     */
    private Short days;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_coupon.start_time
     *
     * @mbggenerated
     */
    private Date startTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_coupon.end_time
     *
     * @mbggenerated
     */
    private Date endTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_coupon.add_time
     *
     * @mbggenerated
     */
    private Date addTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_coupon.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column librecmall_coupon.deleted
     *
     * @mbggenerated
     */
    private Boolean deleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_coupon.id
     *
     * @return the value of librecmall_coupon.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_coupon.id
     *
     * @param id the value for librecmall_coupon.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_coupon.name
     *
     * @return the value of librecmall_coupon.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_coupon.name
     *
     * @param name the value for librecmall_coupon.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_coupon.desc
     *
     * @return the value of librecmall_coupon.desc
     *
     * @mbggenerated
     */
    public String getDesc() {
        return desc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_coupon.desc
     *
     * @param desc the value for librecmall_coupon.desc
     *
     * @mbggenerated
     */
    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_coupon.tag
     *
     * @return the value of librecmall_coupon.tag
     *
     * @mbggenerated
     */
    public String getTag() {
        return tag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_coupon.tag
     *
     * @param tag the value for librecmall_coupon.tag
     *
     * @mbggenerated
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_coupon.total
     *
     * @return the value of librecmall_coupon.total
     *
     * @mbggenerated
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_coupon.total
     *
     * @param total the value for librecmall_coupon.total
     *
     * @mbggenerated
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_coupon.discount
     *
     * @return the value of librecmall_coupon.discount
     *
     * @mbggenerated
     */
    public BigDecimal getDiscount() {
        return discount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_coupon.discount
     *
     * @param discount the value for librecmall_coupon.discount
     *
     * @mbggenerated
     */
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_coupon.min
     *
     * @return the value of librecmall_coupon.min
     *
     * @mbggenerated
     */
    public BigDecimal getMin() {
        return min;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_coupon.min
     *
     * @param min the value for librecmall_coupon.min
     *
     * @mbggenerated
     */
    public void setMin(BigDecimal min) {
        this.min = min;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_coupon.limit
     *
     * @return the value of librecmall_coupon.limit
     *
     * @mbggenerated
     */
    public Short getLimit() {
        return limit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_coupon.limit
     *
     * @param limit the value for librecmall_coupon.limit
     *
     * @mbggenerated
     */
    public void setLimit(Short limit) {
        this.limit = limit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_coupon.type
     *
     * @return the value of librecmall_coupon.type
     *
     * @mbggenerated
     */
    public Short getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_coupon.type
     *
     * @param type the value for librecmall_coupon.type
     *
     * @mbggenerated
     */
    public void setType(Short type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_coupon.status
     *
     * @return the value of librecmall_coupon.status
     *
     * @mbggenerated
     */
    public Short getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_coupon.status
     *
     * @param status the value for librecmall_coupon.status
     *
     * @mbggenerated
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_coupon.goods_type
     *
     * @return the value of librecmall_coupon.goods_type
     *
     * @mbggenerated
     */
    public Short getGoodsType() {
        return goodsType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_coupon.goods_type
     *
     * @param goodsType the value for librecmall_coupon.goods_type
     *
     * @mbggenerated
     */
    public void setGoodsType(Short goodsType) {
        this.goodsType = goodsType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_coupon.goods_value
     *
     * @return the value of librecmall_coupon.goods_value
     *
     * @mbggenerated
     */
    public String getGoodsValue() {
        return goodsValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_coupon.goods_value
     *
     * @param goodsValue the value for librecmall_coupon.goods_value
     *
     * @mbggenerated
     */
    public void setGoodsValue(String goodsValue) {
        this.goodsValue = goodsValue == null ? null : goodsValue.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_coupon.code
     *
     * @return the value of librecmall_coupon.code
     *
     * @mbggenerated
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_coupon.code
     *
     * @param code the value for librecmall_coupon.code
     *
     * @mbggenerated
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_coupon.time_type
     *
     * @return the value of librecmall_coupon.time_type
     *
     * @mbggenerated
     */
    public Short getTimeType() {
        return timeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_coupon.time_type
     *
     * @param timeType the value for librecmall_coupon.time_type
     *
     * @mbggenerated
     */
    public void setTimeType(Short timeType) {
        this.timeType = timeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_coupon.days
     *
     * @return the value of librecmall_coupon.days
     *
     * @mbggenerated
     */
    public Short getDays() {
        return days;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_coupon.days
     *
     * @param days the value for librecmall_coupon.days
     *
     * @mbggenerated
     */
    public void setDays(Short days) {
        this.days = days;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_coupon.start_time
     *
     * @return the value of librecmall_coupon.start_time
     *
     * @mbggenerated
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_coupon.start_time
     *
     * @param startTime the value for librecmall_coupon.start_time
     *
     * @mbggenerated
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_coupon.end_time
     *
     * @return the value of librecmall_coupon.end_time
     *
     * @mbggenerated
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_coupon.end_time
     *
     * @param endTime the value for librecmall_coupon.end_time
     *
     * @mbggenerated
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_coupon.add_time
     *
     * @return the value of librecmall_coupon.add_time
     *
     * @mbggenerated
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_coupon.add_time
     *
     * @param addTime the value for librecmall_coupon.add_time
     *
     * @mbggenerated
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_coupon.update_time
     *
     * @return the value of librecmall_coupon.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_coupon.update_time
     *
     * @param updateTime the value for librecmall_coupon.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column librecmall_coupon.deleted
     *
     * @return the value of librecmall_coupon.deleted
     *
     * @mbggenerated
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column librecmall_coupon.deleted
     *
     * @param deleted the value for librecmall_coupon.deleted
     *
     * @mbggenerated
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}