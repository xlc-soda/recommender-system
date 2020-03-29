package temp;


public class LibrecmallFeedback {

  private long id;
  private long userId;
  private String username;
  private String mobile;
  private String feedType;
  private String content;
  private long status;
  private long hasPicture;
  private String picUrls;
  private java.sql.Timestamp addTime;
  private java.sql.Timestamp updateTime;
  private long deleted;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }


  public String getFeedType() {
    return feedType;
  }

  public void setFeedType(String feedType) {
    this.feedType = feedType;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public long getHasPicture() {
    return hasPicture;
  }

  public void setHasPicture(long hasPicture) {
    this.hasPicture = hasPicture;
  }


  public String getPicUrls() {
    return picUrls;
  }

  public void setPicUrls(String picUrls) {
    this.picUrls = picUrls;
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
