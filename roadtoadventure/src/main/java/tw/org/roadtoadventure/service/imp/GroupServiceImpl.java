package tw.org.roadtoadventure.service.imp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tw.org.roadtoadventure.dao.GroupDAO;
import tw.org.roadtoadventure.form.CreateGroupForm;
import tw.org.roadtoadventure.service.GroupService;
import tw.org.roadtoadventure.utils.BeanUtility;
import tw.org.roadtoadventure.vo.Group;
import tw.org.roadtoadventure.vo.UserAccount;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupDAO groupDAO;
	
	@Override
	public void create(CreateGroupForm createGroupForm) throws Exception {
		UserAccount user = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Group g = new Group();
		BeanUtility.copyProperties(createGroupForm, g);
		g.setCreateDate(new Date());
		g.setModifyDate(new Date());
		g.setUserAccountByCreateId(user);
		g.setUserAccountByModifyId(user);
		groupDAO.create(g);
	}

}
