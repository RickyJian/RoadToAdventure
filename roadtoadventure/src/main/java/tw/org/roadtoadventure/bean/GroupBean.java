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
	private String groupDescription;
//	UserInGroup
	private String userId;
//	Journey
	private Integer groupJourneyId;
	private String groupJourneyName;
	private Date beginDate;
	private Date endDate;
	private String groupJourneyContent;
	private String overviewPolylineArray [];
	private String overviewPolyline;
	private String locationArray[];
	private String location;
	private Integer groupJourneyDetailId;
	
//	Search type
	private String searchType; 
	
	
	public GroupBean() {
	}
	public GroupBean(Integer groupId) {
		this.groupId = groupId;
	}
	public GroupBean(Integer groupId,Integer groupJourneyId) {
		this.groupId = groupId;
		this.groupJourneyId = groupJourneyId;
	}
	
	
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
	public String getGroupDescription() {
		return groupDescription;
	}
	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	public Integer getGroupJourneyId() {
		return groupJourneyId;
	}
	public void setGroupJourneyId(Integer groupJourneyId) {
		this.groupJourneyId = groupJourneyId;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getGroupJourneyName() {
		return groupJourneyName;
	}
	public void setGroupJourneyName(String groupJourneyName) {
		this.groupJourneyName = groupJourneyName;
	}
	public String getGroupJourneyContent() {
		return groupJourneyContent;
	}
	public void setGroupJourneyContent(String groupJourneyContent) {
		if(groupJourneyContent == null) {
			this.groupJourneyContent = "";
		}else {
			this.groupJourneyContent = groupJourneyContent.trim().equals("")?null:groupJourneyContent.trim();
		}
	}
	public String[] getOverviewPolylineArray() {
		return overviewPolylineArray;
	}
	public void setOverviewPolylineArray(String[] overviewPolylineArray) {
		this.overviewPolylineArray = overviewPolylineArray;
	}
	public String[] getLocationArray() {
		return locationArray;
	}
	public void setLocationArray(String[] locationArray) {
		this.locationArray = locationArray;
	}
	public String getOverviewPolyline() {
		return overviewPolyline;
	}
	public void setOverviewPolyline(String overviewPolyline) {
		this.overviewPolyline = overviewPolyline;
	}
	public Integer getGroupJourneyDetailId() {
		return groupJourneyDetailId;
	}
	public void setGroupJourneyDetailId(Integer groupJourneyDetailId) {
		this.groupJourneyDetailId = groupJourneyDetailId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
