package tw.org.roadtoadventure.bean;

import java.util.Date;
public class PersonalBean {
	
//	Journey
	private Date beginDate;
	private Date endDate;
	private Integer personalJourneyId;
	private String personalJourneyName;
	private String personalJourneyContent;
	private Date createDate;
	private Date modifyDate;
	private String overviewPolyline;
	
//	Detail
	private Integer personalJourneyDetailId;
	private String location;
	public Integer getPersonalJourneyId() {
		return personalJourneyId;
	}
	public void setPersonalJourneyId(Integer personalJourneyId) {
		this.personalJourneyId = personalJourneyId;
	}
	public String getPersonalJourneyName() {
		return personalJourneyName;
	}
	public void setPersonalJourneyName(String personalJourneyName) {
		this.personalJourneyName = personalJourneyName;
	}
	public String getPersonalJourneyContent() {
		return personalJourneyContent;
	}
	public void setPersonalJourneyContent(String personalJourneyContent) {
		this.personalJourneyContent = personalJourneyContent;
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
	public String getOverviewPolyline() {
		return overviewPolyline;
	}
	public void setOverviewPolyline(String overviewPolyline) {
		this.overviewPolyline = overviewPolyline;
	}
	public Integer getPersonalJourneyDetailId() {
		return personalJourneyDetailId;
	}
	public void setPersonalJourneyDetailId(Integer personalJourneyDetailId) {
		this.personalJourneyDetailId = personalJourneyDetailId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
	
	
}
