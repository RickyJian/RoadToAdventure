package tw.org.roadtoadventure.dao.imp;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import tw.org.roadtoadventure.dao.GroupJourneyDAO;
import tw.org.roadtoadventure.vo.GroupJourney;
import tw.org.roadtoadventure.vo.GroupJourneyDetail;
import tw.org.roadtoadventure.vo.UserInGroup;

@Repository
public class GroupJourneyDAOImpl extends BaseDAOImpl<GroupJourney> implements GroupJourneyDAO {

	@Override
	public List<GroupJourney> readAllWithJoin() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(GroupJourney.class);
		detachedCriteria.createAlias("group", "g");
		return this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

}
