package temp;


public class LibrecmallAftersale {

  private long id;
  private String aftersaleSn;
  private long orderId;
  private long userId;
  private long type;
  private String reason;
  private double amount;
  private String pictures;
  private String comment;
  private long status;
  private java.sql.Timestamp handleTime;
  private java.sql.Timestamp addTime;
  private java.sql.Timestamp updateTime;
  private long deleted;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getAftersaleSn() {
    return aftersaleSn;
  }

  public void setAftersaleSn(String aftersaleSn) {
    this.aftersaleSn = aftersaleSn;
  }


  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
  }


  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }


  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }


  public String getPictures() {
    return pictures;
  }

  public void setPictures(String pictures) {
    this.pictures = pictures;
  }


  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public java.sql.Timestamp getHandleTime() {
    return handleTime;
  }

  public void setHandleTime(java.sql.Timestamp handleTime) {
    this.handleTime = handleTime;
  }


  public java.sql.Timestamp getAddTime() {
    return addTime;
  }

  public void setAddTime(java.sql.Timestamp addTime) {
    this.addTime = addTime;
  }


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }


  public long getDeleted() {
    return deleted;
  }

  public void setDeleted(long deleted) {
    this.deleted = deleted;
  }

}
