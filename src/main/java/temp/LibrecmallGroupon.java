package temp;


public class LibrecmallGroupon {

  private long id;
  private long orderId;
  private long grouponId;
  private long rulesId;
  private long userId;
  private String shareUrl;
  private long creatorUserId;
  private java.sql.Timestamp creatorUserTime;
  private long status;
  private java.sql.Timestamp addTime;
  private java.sql.Timestamp updateTime;
  private long deleted;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }


  public long getGrouponId() {
    return grouponId;
  }

  public void setGrouponId(long grouponId) {
    this.grouponId = grouponId;
  }


  public long getRulesId() {
    return rulesId;
  }

  public void setRulesId(long rulesId) {
    this.rulesId = rulesId;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getShareUrl() {
    return shareUrl;
  }

  public void setShareUrl(String shareUrl) {
    this.shareUrl = shareUrl;
  }


  public long getCreatorUserId() {
    return creatorUserId;
  }

  public void setCreatorUserId(long creatorUserId) {
    this.creatorUserId = creatorUserId;
  }


  public java.sql.Timestamp getCreatorUserTime() {
    return creatorUserTime;
  }

  public void setCreatorUserTime(java.sql.Timestamp creatorUserTime) {
    this.creatorUserTime = creatorUserTime;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
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
