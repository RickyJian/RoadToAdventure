package tw.org.roadtoadventure.dao.imp;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.org.roadtoadventure.dao.UserAccountDAO;
import tw.org.roadtoadventure.bean.UserBean;
import tw.org.roadtoadventure.vo.UserAccount;

@Repository
public class UserAccountDAOImpl extends BaseDAOImpl<UserAccount> implements UserAccountDAO {

	@Override
	public UserAccount readUserForLogin(String userId, String password) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserAccount.class);
		detachedCriteria.add(Restrictions.eq("userId", userId));
		detachedCriteria.add(Restrictions.eq("password", password));
		List<UserAccount> uaList = this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if(uaList.size()==1) {
			return uaList.get(0);
		}
		return null;
	}

	@Override
	public List<UserAccount> readByParameter(UserBean userBean) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserAccount.class);
		if(userBean!=null) {
			if(userBean.getUserId()!=null) {
				detachedCriteria.add(Restrictions.eq("userId", userBean.getUserId()));
			}
			if(userBean.getUserName()!=null&&!userBean.getUserName().equals("")) {
				if(userBean.getSearchType().equals("like")) {
					detachedCriteria.add(Restrictions.like("userName", "%"+userBean.getUserName()+"%"));
				}else {
					detachedCriteria.add(Restrictions.eq("userName", userBean.getUserName()));
				}
			}
		}
		return this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

}
