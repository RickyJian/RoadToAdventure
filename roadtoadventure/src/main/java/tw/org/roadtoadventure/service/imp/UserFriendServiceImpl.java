package tw.org.roadtoadventure.service.imp;

import java.util.Date;

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

}
