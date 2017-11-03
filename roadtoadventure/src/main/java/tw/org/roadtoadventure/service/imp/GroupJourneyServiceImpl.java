package tw.org.roadtoadventure.service.imp;

import java.util.Date;

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

}
