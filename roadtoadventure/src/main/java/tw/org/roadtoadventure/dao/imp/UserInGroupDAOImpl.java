package tw.org.roadtoadventure.dao.imp;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.org.roadtoadventure.dao.UserInGroupDAO;
import tw.org.roadtoadventure.vo.UserInGroup;

@Repository
public class UserInGroupDAOImpl extends BaseDAOImpl<UserInGroup> implements UserInGroupDAO {

	@Override
	public List<UserInGroup> readByUserId(String userId) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserInGroup.class);
		detachedCriteria.createAlias("group", "g");
		detachedCriteria.createAlias("userAccount", "u");
		detachedCriteria.add(Restrictions.eq("id.userId", userId));
		return this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}


}
