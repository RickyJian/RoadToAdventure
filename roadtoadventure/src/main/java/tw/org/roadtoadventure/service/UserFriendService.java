package tw.org.roadtoadventure.service;

import java.util.List;

import tw.org.roadtoadventure.bean.UserBean;

public interface UserFriendService {
	
	public void update (UserBean userBean) throws Exception;
	
	public void updateAccept (String friendId) throws Exception;
	
	public void createFriend (String friendId) throws Exception;
	
	public List<UserBean> readAllWithJoin () throws Exception;

}
