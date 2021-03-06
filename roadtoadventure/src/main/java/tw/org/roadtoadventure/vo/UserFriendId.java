package tw.org.roadtoadventure.vo;
// Generated 2017/11/3 �W�� 02:21:29 by Hibernate Tools 5.2.5.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UserFriendId generated by hbm2java
 */
@Embeddable
public class UserFriendId implements java.io.Serializable {

	private String userId;
	private String friendId;

	public UserFriendId() {
	}

	public UserFriendId(String userId, String friendId) {
		this.userId = userId;
		this.friendId = friendId;
	}

	@Column(name = "UserID", nullable = false, length = 20)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "FriendID", nullable = false, length = 20)
	public String getFriendId() {
		return this.friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserFriendId))
			return false;
		UserFriendId castOther = (UserFriendId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this.getUserId() != null
				&& castOther.getUserId() != null && this.getUserId().equals(castOther.getUserId())))
				&& ((this.getFriendId() == castOther.getFriendId()) || (this.getFriendId() != null
						&& castOther.getFriendId() != null && this.getFriendId().equals(castOther.getFriendId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result + (getFriendId() == null ? 0 : this.getFriendId().hashCode());
		return result;
	}

}
