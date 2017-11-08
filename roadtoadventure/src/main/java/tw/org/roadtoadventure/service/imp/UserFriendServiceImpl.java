package tw.org.roadtoadventure.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.org.roadtoadventure.bean.UserBean;
import tw.org.roadtoadventure.dao.UserFriendDAO;
import tw.org.roadtoadventure.service.UserFriendService;
import tw.org.roadtoadventure.vo.UserAccount;
import tw.org.roadtoadventure.vo.UserFriend;
import tw.org.roadtoadventure.vo.UserFriendId;

@Service
public class UserFriendServiceImpl implements UserFriendService {
	
	@Autowired
	private UserFriendDAO userFriendDAO;

	@Override
	public void update(UserBean userBean) throws Exception {
		UserFriend userFriend = new UserFriend();
		UserFriendId id = new UserFriendId();
		userFriend.setStatus(userBean.getStatus());
		id.setFriendId(userBean.getFriendId());
		id.setUserId(userBean.getUserId());
		userFriend.setId(id);
		userFriendDAO.merge(userFriend);
	}

	@Transactional
	@Override
	public void createFriend(String friendId) throws Exception {
//		status 邀請人 3 被邀請 0
		UserAccount user = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserFriend userFriend = new UserFriend();
		UserFriendId id = new UserFriendId();
		userFriend.setStatus('3');
		id.setFriendId(friendId);
		id.setUserId(user.getUserId());
		userFriend.setId(id);
		userFriend.setCreateDate(new Date());
		userFriendDAO.create(userFriend);
		UserFriend userFriend2 = new UserFriend();
		UserFriendId id2 = new UserFriendId();
		userFriend2.setStatus('0');
		id2.setFriendId(user.getUserId());
		id2.setUserId(friendId);
		userFriend2.setId(id2);
		userFriend2.setCreateDate(new Date());
		userFriendDAO.create(userFriend2);
		
    }

	@Override
	public List<UserBean> readAllWithJoin() throws Exception {
		UserAccount user = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<UserFriend> ufList = userFriendDAO.readAllByUesrId(user.getUserId());
		List<UserBean> ubList = new ArrayList<>();
		for(UserFriend userFriend: ufList) {
			UserBean userBean = new UserBean();
			userBean.setUserName(userFriend.getUserAccountByFriendId().getUserName());
			userBean.setUserId(userFriend.getId().getUserId());
			userBean.setFriendId(userFriend.getId().getFriendId());
			userBean.setCreateDate(userFriend.getCreateDate());
			userBean.setStatus(userFriend.getStatus());
			ubList.add(userBean);
		}
		return ubList;
	}

	@Transactional
	@Override
	public void updateAccept(String friendId) throws Exception {
		UserAccount user = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserFriendId id = new UserFriendId();
		id.setUserId(user.getUserId());
		id.setFriendId(friendId);
		UserFriend userFriend = userFriendDAO.getById(id);
//		UserFriend userFriend = new UserFriend();
		userFriend.setStatus('1');
		userFriendDAO.merge(userFriend);
		UserFriendId id2 = new UserFriendId();
		id2.setFriendId(user.getUserId());
		id2.setUserId(friendId);
		UserFriend userFriend2 = userFriendDAO.getById(id);
		userFriend2.setStatus('1');
		userFriendDAO.merge(userFriend2);
	}

}
