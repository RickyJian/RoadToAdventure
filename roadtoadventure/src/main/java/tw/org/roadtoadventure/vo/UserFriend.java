package tw.org.roadtoadventure.vo;
// Generated 2017/11/3 �W�� 02:21:29 by Hibernate Tools 5.2.5.Final

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * UserFriend generated by hbm2java
 */
@Entity
@Table(name = "UserFriend", schema = "dbo", catalog = "RoadToAdventure")
public class UserFriend implements java.io.Serializable {

	private UserFriendId id;
	private UserAccount userAccountByUserId;
	private UserAccount userAccountByFriendId;
	private Date createDate;
	private char status;

	public UserFriend() {
	}

	public UserFriend(UserFriendId id, UserAccount userAccountByUserId, UserAccount userAccountByFriendId,
			Date createDate, char status) {
		this.id = id;
		this.userAccountByUserId = userAccountByUserId;
		this.userAccountByFriendId = userAccountByFriendId;
		this.createDate = createDate;
		this.status = status;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "userId", column = @Column(name = "UserID", nullable = false, length = 20)),
			@AttributeOverride(name = "friendId", column = @Column(name = "FriendID", nullable = false, length = 20)) })
	public UserFriendId getId() {
		return this.id;
	}

	public void setId(UserFriendId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UserID", nullable = false, insertable = false, updatable = false)
	public UserAccount getUserAccountByUserId() {
		return this.userAccountByUserId;
	}

	public void setUserAccountByUserId(UserAccount userAccountByUserId) {
		this.userAccountByUserId = userAccountByUserId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FriendID", nullable = false, insertable = false, updatable = false)
	public UserAccount getUserAccountByFriendId() {
		return this.userAccountByFriendId;
	}

	public void setUserAccountByFriendId(UserAccount userAccountByFriendId) {
		this.userAccountByFriendId = userAccountByFriendId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreateDate", nullable = false, length = 23)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "Status", nullable = false, length = 1)
	public char getStatus() {
		return this.status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

}
