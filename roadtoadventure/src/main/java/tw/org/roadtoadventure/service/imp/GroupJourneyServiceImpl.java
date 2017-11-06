package tw.org.roadtoadventure.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.org.roadtoadventure.bean.GroupBean;
import tw.org.roadtoadventure.dao.GroupJourneyDAO;
import tw.org.roadtoadventure.dao.GroupJourneyDetailDAO;
import tw.org.roadtoadventure.service.GroupJourneyService;
import tw.org.roadtoadventure.utils.BeanUtility;
import tw.org.roadtoadventure.vo.Group;
import tw.org.roadtoadventure.vo.GroupJourney;
import tw.org.roadtoadventure.vo.GroupJourneyDetail;
import tw.org.roadtoadventure.vo.UserAccount;

@Service
public class GroupJourneyServiceImpl implements GroupJourneyService {

	@Autowired
	private GroupJourneyDAO groupJourneyDAO;
	@Autowired
	private GroupJourneyDetailDAO groupJourneyDetailDAO;

	@Override
	public void create(GroupBean groupBean) throws Exception {
		UserAccount user = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GroupJourney gj = new GroupJourney();
		BeanUtility.copyProperties(groupBean, gj);
		Group g = new Group();
		g.setGroupId(groupBean.getGroupId());
		gj.setGroup(g);
		gj.setUserAccountByCreateId(user);
		gj.setCreateDate(new Date());
		groupJourneyDAO.create(gj);
	}

	@Override
	public List<GroupBean> readAll() throws Exception {
		List<GroupBean> gbList = new ArrayList<GroupBean>();
		List<GroupJourney> uigList = groupJourneyDAO.readAllWithJoin();
		for(GroupJourney gj :uigList) {
			GroupBean gb = new GroupBean();
			BeanUtility.copyProperties(gj, gb);
			gb.setGroupId(gj.getGroup().getGroupId());
			gbList.add(gb);
		}
		return gbList;
	}

	@Override
	public List<GroupBean> readByParameter(GroupBean groupBean) throws Exception {
		List<GroupJourney> gjList  = groupJourneyDAO.readByParameter(groupBean);
		List<GroupBean> gbList = new ArrayList<GroupBean>();
		for(GroupJourney gj :gjList) {
			GroupBean gb = new GroupBean();
			BeanUtility.copyProperties(gj, gb);
			gb.setGroupId(gj.getGroup().getGroupId());
			gbList.add(gb);
		}
		return gbList;
	}

	@Transactional
	@Override
	public void update(GroupBean groupBean) throws Exception {
		UserAccount user = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<GroupJourneyDetail> gjdList = groupJourneyDetailDAO.readByParamter(groupBean);			
		for(GroupJourneyDetail gjd:gjdList) {
			groupJourneyDetailDAO.delete(gjd);
		}
		//		insert journey detail
		for(int i = 0 ; i < groupBean.getLocationArray().length ; i++ ) {
			GroupJourneyDetail groupJourneyDetail = new GroupJourneyDetail();
			String location = groupBean.getLocationArray()[i];
			//			String overviewPolyline = groupBean.getOverviewPolylineArray()[i];
			groupJourneyDetail.setLocation(location);
			groupJourneyDetail.setUserAccount(user);
			groupJourneyDetail.setCreateDate(new Date());
			GroupJourney gj = new GroupJourney();
			gj.setGroupJourneyId(groupBean.getGroupJourneyId());
			groupJourneyDetail.setGroupJourney(gj);
			//			groupJourneyDetail.setOverviewPolyline(overviewPolyline);
			groupJourneyDetailDAO.create(groupJourneyDetail);
		}
		GroupJourney groupJourney = groupJourneyDAO.getById(groupBean.getGroupJourneyId());
		groupJourney.setGroupJourneyContent(groupBean.getGroupJourneyContent());
		groupJourney.setOverviewPolyline(groupBean.getOverviewPolyline());
		groupJourney.setModifyTime(new Date());
		groupJourney.setUserAccountByModifyId(user);
		groupJourneyDAO.merge(groupJourney);
	}

	@Override
	public List<GroupBean> readDetailByParameter(GroupBean groupBean) throws Exception {
		List<GroupJourneyDetail> gjdList = groupJourneyDetailDAO.readByParamter(groupBean);
		List<GroupBean> gbList = new ArrayList<>();
		for(GroupJourneyDetail gjd :gjdList ) {
			GroupBean gb=new GroupBean();
			BeanUtility.copyProperties(gjd, gb);
			gb.setOverviewPolyline(gjd.getGroupJourney().getOverviewPolyline());
			gb.setBeginDate(gjd.getGroupJourney().getBeginDate());
			gb.setEndDate(gjd.getGroupJourney().getEndDate());
			gb.setGroupId(gjd.getGroupJourney().getGroup().getGroupId());
			gb.setGroupJourneyId(gjd.getGroupJourney().getGroupJourneyId());
			gb.setGroupJourneyDetailId(gjd.getGroupJourneyDetailId());
			gb.setGroupJourneyName(gjd.getGroupJourney().getGroupJourneyName());
			gb.setGroupJourneyContent(gjd.getGroupJourney().getGroupJourneyContent());
			gbList.add(gb);
		}
		return gbList;
	}

}
