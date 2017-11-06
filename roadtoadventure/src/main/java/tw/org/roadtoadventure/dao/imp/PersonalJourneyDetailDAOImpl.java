package tw.org.roadtoadventure.dao.imp;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import tw.org.roadtoadventure.bean.PersonalBean;
import tw.org.roadtoadventure.dao.PersonalJourneyDetailDAO;
import tw.org.roadtoadventure.vo.PersonalJourneyDetail;

@Repository
public class PersonalJourneyDetailDAOImpl extends BaseDAOImpl<PersonalJourneyDetail>
		implements PersonalJourneyDetailDAO {

	@Override
	public List<PersonalJourneyDetail> readByParamter(PersonalBean personalBean) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PersonalJourneyDetail.class);
		detachedCriteria.createAlias("personalJourney", "pj");
		if(personalBean!=null) {
			if(personalBean.getPersonalJourneyId()!=null) {
				detachedCriteria.add(Restrictions.eq("pj.personalJourneyId", personalBean.getPersonalJourneyId()));
				
			}
		}
		return this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	
}
