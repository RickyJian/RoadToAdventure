package tw.org.roadtoadventure.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.org.roadtoadventure.bean.GroupBean;
import tw.org.roadtoadventure.dao.GroupDAO;
import tw.org.roadtoadventure.dao.UserInGroupDAO;
import tw.org.roadtoadventure.service.GroupService;
import tw.org.roadtoadventure.utils.BeanUtility;
import tw.org.roadtoadventure.vo.Group;
import tw.org.roadtoadventure.vo.GroupRole;
import tw.org.roadtoadventure.vo.UserAccount;
import tw.org.roadtoadventure.vo.UserInGroup;
import tw.org.roadtoadventure.vo.UserInGroupId;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupDAO groupDAO;
	@Autowired
	private UserInGroupDAO userInGroupDAO;
	
	@Transactional
	@Override
	public void create(GroupBean groupBean) throws Exception {
		UserAccount user = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		add group
		Group g = new Group();
		BeanUtility.copyProperties(groupBean, g);
		g.setStatus('1');
		g.setCreateDate(new Date());
		g.setModifyDate(new Date());
		g.setUserAccountByCreateId(user);
		g.setUserAccountByModifyId(user);
		groupDAO.create(g);
		
//		add useringroup
		UserInGroup uig = new UserInGroup();
		uig.setStatus('1');
		UserInGroupId uigId = new UserInGroupId();
		uigId.setGroupId(g.getGroupId());
		uigId.setUserId(user.getUserId());
		uig.setId(uigId);
		uig.setCreateDate(new Date());
		
		GroupRole gr = new GroupRole();
		gr.setGroupRoleId("GR0");
		uig.setGroupRole(gr);
		userInGroupDAO.create(uig);
	}

	@Override
	public GroupBean readByGroupId(Integer groupId) throws Exception {
		Group g = groupDAO.getById(groupId);
		GroupBean gb= new GroupBean();
		BeanUtility.copyProperties(g, gb);
		return gb;
	}

	@Override
	public void update(GroupBean groupBean) throws Exception {
		UserAccount user = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Group g = groupDAO.getById(groupBean.getGroupId());
		BeanUtility.copyProperties(groupBean,g);
		g.setGroupPicture(groupBean.getGroupPicture().trim());
		g.setModifyDate(new Date());
		g.setUserAccountByModifyId(user);
		groupDAO.merge(g);
	}

	@Override
	public List<GroupBean> readAll() throws Exception {
		List<Group> gList = groupDAO.readAll();
		List<GroupBean> gbList = new ArrayList<>();
		for(Group g : gList) {
			GroupBean gb = new GroupBean();
			BeanUtility.copyProperties(g, gb);
			gbList.add(gb);
		}
		return gbList;
	}

}
