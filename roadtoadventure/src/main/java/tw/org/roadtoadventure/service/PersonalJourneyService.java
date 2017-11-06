package tw.org.roadtoadventure.service;

import java.util.List;

import tw.org.roadtoadventure.bean.PersonalBean;

public interface PersonalJourneyService {

	public void create(PersonalBean personalBean) throws Exception;
	
	public List<PersonalBean> readAllByUserId () throws Exception;
	
	public List<PersonalBean> readByParameter (PersonalBean personalBean) throws Exception;
	
	public List<PersonalBean> readDetailByParameter (PersonalBean personalBean) throws Exception;
	
}
