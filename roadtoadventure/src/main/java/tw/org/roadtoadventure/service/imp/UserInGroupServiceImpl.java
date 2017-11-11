package tw.org.roadtoadventure.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tw.org.roadtoadventure.bean.GroupBean;
import tw.org.roadtoadventure.dao.UserInGroupDAO;
import tw.org.roadtoadventure.service.UserInGroupService;
import tw.org.roadtoadventure.utils.BeanUtility;
import tw.org.roadtoadventure.vo.GroupRole;
import tw.org.roadtoadventure.vo.UserAccount;
import tw.org.roadtoadventure.vo.UserInGroup;
import tw.org.roadtoadventure.vo.UserInGroupId;

@Service
public class UserInGroupServiceImpl implements UserInGroupService{
	
	@Autowired
	private UserInGroupDAO userInGroupDAO;

	@Override
	public void create(GroupBean groupBean) throws Exception {
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
		userInGroup.setCreateDate(new Date());
		userInGroupDAO.create(userInGroup);
	}
	
	@Override
	public void update(GroupBean groupBean) throws Exception {
		UserInGroupId uigId = new UserInGroupId();
		uigId.setGroupId(groupBean.getGroupId());
		uigId.setUserId(groupBean.getUserId());
		UserInGroup userInGroup = userInGroupDAO.getById(uigId);
		BeanUtility.copyProperties(groupBean, userInGroup);
		userInGroup.setStatus(groupBean.getStatus());
		userInGroupDAO.merge(userInGroup);
	}

	@Override
	public void delete(String userId ,Integer groupId) throws Exception {
		UserInGroupId uigId = new UserInGroupId();
		uigId.setGroupId(groupId);
		uigId.setUserId(userId);
		UserInGroup userInGroup = userInGroupDAO.getById(uigId);
		userInGroupDAO.delete(userInGroup);
	}
	
//	@Override
//	public List<GroupBean> readAllWithoutUserId() throws Exception {
//		UserAccount user = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		List<GroupBean> gbList = new ArrayList<GroupBean>();
//		List<UserInGroup> uigList = userInGroupDAO.readAllWithoutUserId(user.getUserId());
//		for(UserInGroup uig :uigList) {
//			GroupBean gb = new GroupBean();
//			gb.setUserId(uig.getId().getUserId());
//			gb.setGroupId(uig.getId().getGroupId());
//			gb.setGroupName(uig.getGroup().getGroupName());
//			gb.setGroupPicture(uig.getGroup().getGroupPicture());
//			gb.setCreateDate(uig.getGroup().getCreateDate());
//			gbList.add(gb);
//		}
//		return gbList;
//	}
	
	@Override
	public List<GroupBean> readAllByUserId() throws Exception {
		UserAccount user = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<GroupBean> gbList = new ArrayList<GroupBean>();
		List<UserInGroup> uigList = userInGroupDAO.readByUserId(user.getUserId());
		for(UserInGroup uig :uigList) {
			GroupBean gb = new GroupBean();
			gb.setUserId(uig.getId().getUserId());
			gb.setStatus(uig.getStatus());
			gb.setGroupId(uig.getId().getGroupId());
			gb.setGroupDescription(uig.getGroup().getGroupDescription()==null?"":uig.getGroup().getGroupDescription());
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
			gb.setGroupDescription(uig.getGroup().getGroupDescription()==null?"":uig.getGroup().getGroupDescription());
			gb.setGroupName(uig.getGroup().getGroupName());
			gb.setGroupPicture(uig.getGroup().getGroupPicture());
			gb.setCreateDate(uig.getGroup().getCreateDate());
			gbList.add(gb);
		}
		return gbList;
	}
	
	@Override
	public List<GroupBean> readByParameter(GroupBean groupBean) throws Exception {
		List<GroupBean> gbList = new ArrayList<GroupBean>();
		List<UserInGroup> uigList = userInGroupDAO.readByParameter(groupBean);
		for(UserInGroup uig :uigList) {
			GroupBean gb = new GroupBean();
			gb.setUserId(uig.getId().getUserId());
			gb.setUserName(uig.getUserAccount().getUserName());
			gb.setUserPicture(uig.getUserAccount().getUserPicture());
			gb.setStatus(uig.getStatus());
			gb.setGroupId(uig.getId().getGroupId());
			gb.setGroupName(uig.getGroup().getGroupName());
			gb.setGroupPicture(uig.getGroup().getGroupPicture());
			gb.setCreateDate(uig.getGroup().getCreateDate());
			gbList.add(gb);
		}
		return gbList;
	}
}
