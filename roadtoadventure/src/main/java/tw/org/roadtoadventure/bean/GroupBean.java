package tw.org.roadtoadventure.bean;

import java.util.Date;

import tw.org.roadtoadventure.vo.UserAccount;

public class GroupBean {
	private Integer groupId;
	private String groupRoleId;
	private String groupName;
	private String groupPicture;
	private Date createDate;
	private Date modifyDate;
	private char status;
	
//	UserInGroup
	private String userId;

//	Search type
	private String searchType; 
	
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupPicture() {
		return groupPicture;
	}
	public void setGroupPicture(String groupPicture) {
		this.groupPicture = groupPicture;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public String getGroupRoleId() {
		return groupRoleId;
	}
	public void setGroupRoleId(String groupRoleId) {
		this.groupRoleId = groupRoleId;
	}
	
	
}
