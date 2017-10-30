package tw.org.roadtoadventure.vo;
// Generated 2017/10/30 �U�� 10:59:10 by Hibernate Tools 5.2.5.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PersonalJourneyDetail generated by hbm2java
 */
@Entity
@Table(name = "PersonalJourneyDetail", schema = "dbo", catalog = "RoadToAdventure")
public class PersonalJourneyDetail implements java.io.Serializable {

	private PersonalJourneyDetailId id;
	private PersonalJourney personalJourney;
	private String latitude;
	private String longitude;
	private String overviewPolyline;

	public PersonalJourneyDetail() {
	}

	public PersonalJourneyDetail(PersonalJourneyDetailId id, PersonalJourney personalJourney) {
		this.id = id;
		this.personalJourney = personalJourney;
	}

	public PersonalJourneyDetail(PersonalJourneyDetailId id, PersonalJourney personalJourney, String latitude,
			String longitude, String overviewPolyline) {
		this.id = id;
		this.personalJourney = personalJourney;
		this.latitude = latitude;
		this.longitude = longitude;
		this.overviewPolyline = overviewPolyline;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "personalJourneyId", column = @Column(name = "PersonalJourneyID", nullable = false)),
			@AttributeOverride(name = "userId", column = @Column(name = "UserID", nullable = false, length = 20)),
			@AttributeOverride(name = "createDate", column = @Column(name = "CreateDate", nullable = false, length = 23)) })
	public PersonalJourneyDetailId getId() {
		return this.id;
	}

	public void setId(PersonalJourneyDetailId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "PersonalJourneyID", referencedColumnName = "PersonalJourneyID", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "UserID", referencedColumnName = "UserID", nullable = false, insertable = false, updatable = false) })
	public PersonalJourney getPersonalJourney() {
		return this.personalJourney;
	}

	public void setPersonalJourney(PersonalJourney personalJourney) {
		this.personalJourney = personalJourney;
	}

	@Column(name = "Latitude", length = 30)
	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name = "Longitude", length = 30)
	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name = "OverviewPolyline")
	public String getOverviewPolyline() {
		return this.overviewPolyline;
	}

	public void setOverviewPolyline(String overviewPolyline) {
		this.overviewPolyline = overviewPolyline;
	}

}
