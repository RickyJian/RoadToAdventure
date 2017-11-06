package tw.org.roadtoadventure.dao.imp;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.org.roadtoadventure.bean.PersonalBean;
import tw.org.roadtoadventure.dao.PersonalJourneyDAO;
import tw.org.roadtoadventure.vo.PersonalJourney;

@Repository
public class PersonalJourneyDAOImpl extends BaseDAOImpl<PersonalJourney> implements PersonalJourneyDAO {

	@Override
	public List<PersonalJourney> readByParameter(PersonalBean personalBean) {
		DetachedCriteria  detachedCriteria = DetachedCriteria.forClass(PersonalJourney.class);
		detachedCriteria.createAlias("userAccountByCreateId", "user");
		if(personalBean!=null) {
			if(personalBean.getCreateId()!=null&&!personalBean.getCreateId().equals("")) {
				detachedCriteria.add(Restrictions.eq("user.userId", personalBean.getCreateId()));
			}
			if(personalBean.getPersonalJourneyId()!=null) {
				detachedCriteria.add(Restrictions.eq("personalJourneyId", personalBean.getPersonalJourneyId()));
			}
		}
		return this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

}
