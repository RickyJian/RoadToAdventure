package tw.org.roadtoadventure.service.imp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tw.org.roadtoadventure.bean.PersonalBean;
import tw.org.roadtoadventure.dao.PersonalJourneyDAO;
import tw.org.roadtoadventure.service.PersonalJourneyService;
import tw.org.roadtoadventure.utils.BeanUtility;
import tw.org.roadtoadventure.vo.PersonalJourney;
import tw.org.roadtoadventure.vo.UserAccount;

@Service
public class PersonalJourneyServiceImpl implements PersonalJourneyService {

	@Autowired
	private PersonalJourneyDAO personalJourneyDAO;
	
	@Override
	public void create(PersonalBean personalBean) throws Exception {
		UserAccount user = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonalJourney pj = new PersonalJourney();
		BeanUtility.copyProperties(personalBean, pj);
		pj.setCreateDate(new Date());
		pj.setUserAccountByCreateId(user);
		personalJourneyDAO.create(pj);
	}

}
