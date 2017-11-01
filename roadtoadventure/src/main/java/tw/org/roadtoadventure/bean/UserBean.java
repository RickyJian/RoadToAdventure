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
	protected String getUserId() {
		return userId;
	}
	protected void setUserId(String userId) {
		this.userId = userId;
	}
	protected String getUserName() {
		return userName;
	}
	protected void setUserName(String userName) {
		this.userName = userName;
	}
	protected String getUserPicture() {
		return userPicture;
	}
	protected void setUserPicture(String userPicture) {
		this.userPicture = userPicture;
	}
	protected String getUserRoleId() {
		return userRoleId;
	}
	protected void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}
	protected String getEmail() {
		return email;
	}
	protected void setEmail(String email) {
		this.email = email;
	}
	protected String getPassword() {
		return password;
	}
	protected void setPassword(String password) {
		this.password = password;
	}
	protected String getLastPassword() {
		return lastPassword;
	}
	protected void setLastPassword(String lastPassword) {
		this.lastPassword = lastPassword;
	}
	protected Date getLastLoginTime() {
		return lastLoginTime;
	}
	protected void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	protected Character getIsEnabled() {
		return isEnabled;
	}
	protected void setIsEnabled(Character isEnabled) {
		this.isEnabled = isEnabled;
	}
	protected Character getIsVerification() {
		return isVerification;
	}
	protected void setIsVerification(Character isVerification) {
		this.isVerification = isVerification;
	}
	protected String getCreateId() {
		return createId;
	}
	protected void setCreateId(String createId) {
		this.createId = createId;
	}
	protected Date getCreateDate() {
		return createDate;
	}
	protected void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	protected String getModifyId() {
		return modifyId;
	}
	protected void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}
	protected Date getModifyDate() {
		return modifyDate;
	}
	protected void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	

}
