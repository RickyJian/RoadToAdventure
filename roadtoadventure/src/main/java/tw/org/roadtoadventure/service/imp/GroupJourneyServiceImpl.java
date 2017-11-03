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
import tw.org.roadtoadventure.service.GroupJourneyService;
import tw.org.roadtoadventure.utils.BeanUtility;
import tw.org.roadtoadventure.vo.Group;
import tw.org.roadtoadventure.vo.GroupJourney;
import tw.org.roadtoadventure.vo.UserAccount;
import tw.org.roadtoadventure.vo.UserInGroup;

@Service
public class GroupJourneyServiceImpl implements GroupJourneyService {
	
	@Autowired
	private GroupJourneyDAO groupJourneyDAO;

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

}
