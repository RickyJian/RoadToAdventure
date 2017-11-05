package tw.org.roadtoadventure.vo;
// Generated 2017/11/3 �W�� 02:21:29 by Hibernate Tools 5.2.5.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * GroupJourney generated by hbm2java
 */
@Entity
@Table(name = "GroupJourney", schema = "dbo", catalog = "RoadToAdventure")
public class GroupJourney implements java.io.Serializable {

	private Integer groupJourneyId;
	private Group group;
	private UserAccount userAccountByCreateId;
	private UserAccount userAccountByModifyId;
	private String groupJourneyName;
	private String groupJourneyContent;
	private String overviewPolyline;
	private Date createDate;
	private Date modifyTime;
	private Date beginDate;
	private Date endDate;
	private Set<GroupJourneyDetail> groupJourneyDetails = new HashSet<GroupJourneyDetail>(0);

	public GroupJourney() {
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "GroupJourneyID", unique = true, nullable = false)
	public Integer getGroupJourneyId() {
		return this.groupJourneyId;
	}

	public void setGroupJourneyId(Integer groupJourneyId) {
		this.groupJourneyId = groupJourneyId;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GroupID", nullable = false)
	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CreateID", nullable = false)
	public UserAccount getUserAccountByCreateId() {
		return this.userAccountByCreateId;
	}

	public void setUserAccountByCreateId(UserAccount userAccountByCreateId) {
		this.userAccountByCreateId = userAccountByCreateId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ModifyID")
	public UserAccount getUserAccountByModifyId() {
		return this.userAccountByModifyId;
	}

	public void setUserAccountByModifyId(UserAccount userAccountByModifyId) {
		this.userAccountByModifyId = userAccountByModifyId;
	}

	@Column(name = "GroupJourneyName", nullable = false, length = 50)
	public String getGroupJourneyName() {
		return this.groupJourneyName;
	}

	public void setGroupJourneyName(String groupJourneyName) {
		this.groupJourneyName = groupJourneyName;
	}

	@Column(name = "GroupJourneyContent")
	public String getGroupJourneyContent() {
		return this.groupJourneyContent;
	}

	public void setGroupJourneyContent(String groupJourneyContent) {
		this.groupJourneyContent = groupJourneyContent;
	}
	
	@Column(name = "OverviewPolyline")
	public String getOverviewPolyline() {
		return this.overviewPolyline;
	}

	public void setOverviewPolyline(String overviewPolyline) {
		this.overviewPolyline = overviewPolyline;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreateDate", nullable = false, length = 23)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ModifyTime", length = 23)
	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BeginDate", length = 23)
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EndDate", length = 23)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "groupJourney")
	public Set<GroupJourneyDetail> getGroupJourneyDetails() {
		return this.groupJourneyDetails;
	}

	public void setGroupJourneyDetails(Set<GroupJourneyDetail> groupJourneyDetails) {
		this.groupJourneyDetails = groupJourneyDetails;
	}

}