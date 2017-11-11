package tw.org.roadtoadventure.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.org.roadtoadventure.bean.PersonalBean;
import tw.org.roadtoadventure.dao.PersonalJourneyDAO;
import tw.org.roadtoadventure.dao.PersonalJourneyDetailDAO;
import tw.org.roadtoadventure.service.PersonalJourneyService;
import tw.org.roadtoadventure.utils.BeanUtility;
import tw.org.roadtoadventure.vo.GroupJourney;
import tw.org.roadtoadventure.vo.GroupJourneyDetail;
import tw.org.roadtoadventure.vo.PersonalJourney;
import tw.org.roadtoadventure.vo.PersonalJourneyDetail;
import tw.org.roadtoadventure.vo.UserAccount;

@Service
public class PersonalJourneyServiceImpl implements PersonalJourneyService {

	@Autowired
	private PersonalJourneyDAO personalJourneyDAO;
	@Autowired
	private PersonalJourneyDetailDAO personalJourneyDetailDAO;
	
	@Override
	public void create(PersonalBean personalBean) throws Exception {
		UserAccount user = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonalJourney pj = new PersonalJourney();
		BeanUtility.copyProperties(personalBean, pj);
		pj.setCreateDate(new Date());
		pj.setUserAccountByCreateId(user);
		personalJourneyDAO.create(pj);
	}

	@Override
	public List<PersonalBean> readAllByUserId() throws Exception {
		UserAccount user = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonalBean personalBean = new PersonalBean();
		personalBean.setCreateId(user.getUserId());
		List<PersonalJourney> pjList = personalJourneyDAO.readByParameter(personalBean);
		List<PersonalBean> pbList = new ArrayList<>();
		for(PersonalJourney pj : pjList) {
			PersonalBean pb = new PersonalBean();
			BeanUtility.copyProperties(pj, pb);
			pbList.add(pb);
		}
		return pbList;
	}

	@Override
	public List<PersonalBean> readByParameter(PersonalBean personalBean) throws Exception {
		List<PersonalJourney> pjList = personalJourneyDAO.readByParameter(personalBean);
		List<PersonalBean> pbList = new ArrayList<>();
		for(PersonalJourney pj : pjList) {
			PersonalBean pb = new PersonalBean();
			BeanUtility.copyProperties(pj, pb);
			pbList.add(pb);
		}
		return pbList;
	}

	@Override
	public List<PersonalBean> readDetailByParameter(PersonalBean personalBean) throws Exception {
		List<PersonalJourneyDetail> pjList = personalJourneyDetailDAO.readByParamter(personalBean);
		List<PersonalBean> pbList = new ArrayList<>();
		for(PersonalJourneyDetail pjd : pjList) {
			PersonalBean pb = new PersonalBean();
			BeanUtility.copyProperties(pjd, pb);
			pb.setPersonalJourneyName(pjd.getPersonalJourney().getPersonalJourneyName());
			pb.setBeginDate(pjd.getPersonalJourney().getBeginDate());
			pb.setEndDate(pjd.getPersonalJourney().getEndDate());
			pb.setPersonalJourneyName(pjd.getPersonalJourney().getPersonalJourneyName());
			pb.setLocation(pjd.getLocation());
			pb.setStatus(pjd.getPersonalJourney().getStatus());
			pb.setPersonalJourneyContent(pjd.getPersonalJourney().getPersonalJourneyContent());
			pb.setOverviewPolyline(pjd.getPersonalJourney().getOverviewPolyline());
			pbList.add(pb);
		}
		return pbList;
	}

	@Transactional
	@Override
	public void update(PersonalBean personalBean) throws Exception {
		UserAccount user = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<PersonalJourneyDetail> pjdList = personalJourneyDetailDAO.readByParamter(personalBean);			
		for(PersonalJourneyDetail pjd:pjdList) {
			personalJourneyDetailDAO.delete(pjd);
		}
		//		insert journey detail
		for(int i = 0 ; i < personalBean.getLocationArray().length ; i++ ) {
			PersonalJourneyDetail personalJourneyDetail = new PersonalJourneyDetail();
			String location = personalBean.getLocationArray()[i];
			//			String overviewPolyline = groupBean.getOverviewPolylineArray()[i];
			personalJourneyDetail.setLocation(location);
			personalJourneyDetail.setCreateDate(new Date());
			PersonalJourney pj = new PersonalJourney();
			pj.setPersonalJourneyId(personalBean.getPersonalJourneyId());
			personalJourneyDetail.setPersonalJourney(pj);
			//			groupJourneyDetail.setOverviewPolyline(overviewPolyline);
			personalJourneyDetailDAO.create(personalJourneyDetail);
		}
		PersonalJourney personalJourney = personalJourneyDAO.getById(personalBean.getPersonalJourneyId());
		personalJourney.setPersonalJourneyContent(personalBean.getPersonalJourneyContent());
		personalJourney.setStatus(personalBean.getStatus());
		personalJourney.setBeginDate(personalBean.getBeginDate());
		personalJourney.setEndDate(personalBean.getEndDate());
		personalJourney.setOverviewPolyline(personalBean.getOverviewPolyline());
		personalJourney.setModifyDate(new Date());
		personalJourney.setUserAccountByModifyId(user);
		personalJourneyDAO.merge(personalJourney);
	}

}
