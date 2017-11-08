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
		detachedCriteria.createAlias("userAccountByUserId", "user");
		detachedCriteria.createAlias("userAccountByFriendId", "friend");
		if(userBean!=null) {
			if(userBean.getUserName()!=null&&!userBean.getUserName().equals("")) {
				if(userBean.getSearchType().equals("like")) {
					detachedCriteria.add(Restrictions.like("user.userName", "%"+userBean.getUserName()+"%"));
				}else {
					detachedCriteria.add(Restrictions.eq("user.userName", userBean.getUserName()));
				}
			}
			if(userBean.getUserId()!=null&&!userBean.getUserId().equals("")) {
				detachedCriteria.add(Restrictions.eq("user.userId", userBean.getUserId()));
			}
			if(userBean.getFriendName()!=null&&!userBean.getFriendName().equals("")) {
				if(userBean.getSearchType().equals("like")) {
					detachedCriteria.add(Restrictions.like("friend.userName", "%"+userBean.getFriendName()+"%"));
				}else {
					detachedCriteria.add(Restrictions.eq("friend.userName", userBean.getFriendName()));
				}
			}
		}
		return this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	@Override
	public List<UserFriend> readAllByUesrId(String userId) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserFriend.class);
		detachedCriteria.createAlias("userAccountByUserId", "user");
		detachedCriteria.add(Restrictions.eq("id.userId", userId));
		return this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

}
