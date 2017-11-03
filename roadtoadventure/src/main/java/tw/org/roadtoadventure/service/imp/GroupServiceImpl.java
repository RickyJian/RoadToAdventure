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
import tw.org.roadtoadventure.form.CreateGroupForm;
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
		
		GroupRole gr = new GroupRole();
		gr.setGroupRoleId("0");
		uig.setGroupRole(gr);
		userInGroupDAO.create(uig);
	}

	@Override
	public List<GroupBean> readAllByUserId() throws Exception {
		UserAccount user = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<GroupBean> gbList = new ArrayList<GroupBean>();
		List<UserInGroup> uigList = userInGroupDAO.readByUserId(user.getUserId());
		for(UserInGroup uig :uigList) {
			GroupBean gb = new GroupBean();
			gb.setUserId(uig.getId().getUserId());
			gb.setGroupId(uig.getId().getGroupId());
			gb.setGroupName(uig.getGroup().getGroupName());
			gb.setGroupPicture(uig.getGroup().getGroupPicture());
			gb.setCreateDate(uig.getGroup().getCreateDate());
			gbList.add(gb);
		}
		return gbList;
	}

	@Override
	public List<GroupBean> readAll() throws Exception {
		List<GroupBean> gbList = new ArrayList<GroupBean>();
		List<UserInGroup> uigList = userInGroupDAO.readAllWithJoin();
		for(UserInGroup uig :uigList) {
			GroupBean gb = new GroupBean();
			gb.setUserId(uig.getId().getUserId());
			gb.setStatus(uig.getStatus());
			gb.setGroupId(uig.getId().getGroupId());
			gb.setGroupName(uig.getGroup().getGroupName());
			gb.setGroupPicture(uig.getGroup().getGroupPicture());
			gb.setCreateDate(uig.getGroup().getCreateDate());
			gbList.add(gb);
		}
		return gbList;
	}

	@Override
	public List<GroupBean> readByParameter(GroupBean groupBean) {
		List<GroupBean> gbList = new ArrayList<GroupBean>();
		List<UserInGroup> uigList = userInGroupDAO.readByParameter(groupBean);
		for(UserInGroup uig :uigList) {
			GroupBean gb = new GroupBean();
			gb.setUserId(uig.getId().getUserId());
			gb.setStatus(uig.getStatus());
			gb.setGroupId(uig.getId().getGroupId());
			gb.setGroupName(uig.getGroup().getGroupName());
			gb.setGroupPicture(uig.getGroup().getGroupPicture());
			gb.setCreateDate(uig.getGroup().getCreateDate());
			gbList.add(gb);
		}
		return gbList;
	}

	@Override
	public void update(GroupBean groupBean) throws Exception {
		UserAccount user = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserInGroup userInGroup = new UserInGroup();
		BeanUtility.copyProperties(groupBean, userInGroup);
		GroupRole gr = new GroupRole();
		gr.setGroupRoleId(groupBean.getGroupRoleId());
		userInGroup.setGroupRole(gr);
		userInGroup.setUserAccount(user);
		userInGroup.setStatus(groupBean.getStatus());
		UserInGroupId uigId = new UserInGroupId();
		uigId.setGroupId(groupBean.getGroupId());
		uigId.setUserId(user.getUserId());
		userInGroup.setId(uigId);
		userInGroupDAO.merge(userInGroup);
	}

}
