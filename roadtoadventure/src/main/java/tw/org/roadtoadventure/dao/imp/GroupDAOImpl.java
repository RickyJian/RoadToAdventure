package tw.org.roadtoadventure.dao.imp;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.org.roadtoadventure.bean.GroupBean;
import tw.org.roadtoadventure.dao.GroupDAO;
import tw.org.roadtoadventure.vo.Group;

@Repository
public class GroupDAOImpl extends BaseDAOImpl<Group> implements GroupDAO {

	@Override
	public List<Group> readByParameter(GroupBean groupBean) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Group.class);
		if(groupBean!=null) {
			String groupName = groupBean.getGroupName();
			if(groupName!=null&&!groupName.equals("")) {
				detachedCriteria.add(Restrictions.eq("groupName", groupName));
			}
		}
		return this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

}
