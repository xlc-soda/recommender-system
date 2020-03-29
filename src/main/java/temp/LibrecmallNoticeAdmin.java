package temp;


public class LibrecmallNoticeAdmin {

  private long id;
  private long noticeId;
  private String noticeTitle;
  private long adminId;
  private java.sql.Timestamp readTime;
  private java.sql.Timestamp addTime;
  private java.sql.Timestamp updateTime;
  private long deleted;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getNoticeId() {
    return noticeId;
  }

  public void setNoticeId(long noticeId) {
    this.noticeId = noticeId;
  }


  public String getNoticeTitle() {
    return noticeTitle;
  }

  public void setNoticeTitle(String noticeTitle) {
    this.noticeTitle = noticeTitle;
  }


  public long getAdminId() {
    return adminId;
  }

  public void setAdminId(long adminId) {
    this.adminId = adminId;
  }


  public java.sql.Timestamp getReadTime() {
    return readTime;
  }

  public void setReadTime(java.sql.Timestamp readTime) {
    this.readTime = readTime;
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
