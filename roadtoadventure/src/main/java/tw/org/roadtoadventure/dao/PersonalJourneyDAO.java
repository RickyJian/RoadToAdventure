package tw.org.roadtoadventure.dao;

import java.util.List;

import tw.org.roadtoadventure.bean.PersonalBean;
import tw.org.roadtoadventure.vo.PersonalJourney;

public interface PersonalJourneyDAO extends BaseDAO<PersonalJourney> {

	public List<PersonalJourney> readByParameter(PersonalBean personalBean);
	
}
