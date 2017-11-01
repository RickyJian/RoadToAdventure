package tw.org.roadtoadventure.bean;

import java.util.Date;

public class UserBean {
	
	private String userId;
	private String userName;
	private String userPicture;
	private String userRoleId;
	private String email;
	private String password;
	private String lastPassword;
	private Date lastLoginTime;
	private Character isEnabled;
	private Character isVerification;
	private String createId;
	private Date createDate;
	private String modifyId;
	private Date modifyDate;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPicture() {
		return userPicture;
	}
	public void setUserPicture(String userPicture) {
		this.userPicture = userPicture;
	}
	public String getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLastPassword() {
		return lastPassword;
	}
	public void setLastPassword(String lastPassword) {
		this.lastPassword = lastPassword;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Character getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(Character isEnabled) {
		this.isEnabled = isEnabled;
	}
	public Character getIsVerification() {
		return isVerification;
	}
	public void setIsVerification(Character isVerification) {
		this.isVerification = isVerification;
	}
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getModifyId() {
		return modifyId;
	}
	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	
}
