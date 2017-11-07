package tw.org.roadtoadventure.dao.imp;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import tw.org.roadtoadventure.bean.UserBean;
import tw.org.roadtoadventure.dao.UserFriendDAO;
import tw.org.roadtoadventure.vo.UserFriend;

@Repository
public class UserFriendDAOImpl extends BaseDAOImpl<UserFriend> implements UserFriendDAO {

	@Override
	public List<UserFriend> readByParameter(UserBean userBean) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserFriend.class);
		
		return null;
	}

}
