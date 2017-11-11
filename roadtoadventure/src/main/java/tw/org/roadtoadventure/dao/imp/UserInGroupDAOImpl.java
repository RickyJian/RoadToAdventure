package tw.org.roadtoadventure.dao.imp;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.org.roadtoadventure.bean.GroupBean;
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

	@Override
	public List<UserInGroup> readAllWithJoin() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserInGroup.class);
		detachedCriteria.createAlias("group", "g");
		detachedCriteria.createAlias("userAccount", "u");
		return this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	@Override
	public List<UserInGroup> readByParameter(GroupBean groupBean) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserInGroup.class);
		detachedCriteria.createAlias("group", "g");
		detachedCriteria.createAlias("userAccount", "u");
		if(groupBean!=null) {
			String groupName = groupBean.getGroupName();
			Integer groupId = groupBean.getGroupId();
			String userId = groupBean.getUserId();
			if(groupName!=null&&!groupName.equals("")) {
				if(groupBean.getSearchType().equals("like")) {
					detachedCriteria.add(Restrictions.like("g.groupName", "%"+groupName+"%"));
				}else {
					detachedCriteria.add(Restrictions.eq("g.groupName", groupName));
				}
			}
			if(groupId!=null) {
				detachedCriteria.add(Restrictions.eq("g.groupId",groupId));
			}
			if(userId!=null&&!userId.equals("")) {
				detachedCriteria.add(Restrictions.eq("u.userId",userId));
			}
		}
		return this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	@Override
	public List<UserInGroup> readAllWithoutUserId(String userId) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserInGroup.class);
		detachedCriteria.createAlias("group", "g");
		detachedCriteria.createAlias("userAccount", "u");
		detachedCriteria.add(Restrictions.ne("id.userId", userId));
		return this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}



}
