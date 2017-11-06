package tw.org.roadtoadventure.dao.imp;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.org.roadtoadventure.bean.GroupBean;
import tw.org.roadtoadventure.dao.GroupJourneyDetailDAO;
import tw.org.roadtoadventure.vo.GroupJourneyDetail;

@Repository
public class GroupJourneyDetailDAOImpl extends BaseDAOImpl<GroupJourneyDetail> implements GroupJourneyDetailDAO {

	@Override
	public List<GroupJourneyDetail> readByParamter(GroupBean groupBean) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(GroupJourneyDetail.class);
		detachedCriteria.createAlias("groupJourney", "gj");
		detachedCriteria.createAlias("gj.group", "g");
		detachedCriteria.createAlias("userAccount", "u");
		if(groupBean!=null) {
			if(groupBean.getGroupJourneyDetailId()!=null) {
				detachedCriteria.add(Restrictions.eq("groupJourneyDetailId", groupBean.getGroupJourneyDetailId()));
			}
			if(groupBean.getGroupJourneyId()!=null) {
				detachedCriteria.add(Restrictions.eq("gj.groupJourneyId", groupBean.getGroupJourneyId()));
			}
			if(groupBean.getGroupId()!=null ) {
				detachedCriteria.add(Restrictions.eq("g.groupId", groupBean.getGroupId()));
			}
		}
		detachedCriteria.addOrder(Order.asc("groupJourneyDetailId"));
		return this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

}
