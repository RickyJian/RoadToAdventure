package tw.org.roadtoadventure.dao.imp;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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

	@Override
	public List<UserFriend> readAllByUesrId(String userId) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserFriend.class);
		detachedCriteria.createAlias("userAccountByUserId", "user");
		detachedCriteria.add(Restrictions.eq("id.userId", userId));
		return this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

}
