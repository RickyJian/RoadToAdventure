package tw.org.roadtoadventure.dao;

import java.util.List;

import tw.org.roadtoadventure.bean.PersonalBean;
import tw.org.roadtoadventure.vo.PersonalJourneyDetail;

public interface PersonalJourneyDetailDAO extends BaseDAO<PersonalJourneyDetail> {
	
	public List<PersonalJourneyDetail> readByParamter(PersonalBean personalBean);
	
}