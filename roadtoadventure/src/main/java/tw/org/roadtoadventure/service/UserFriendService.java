package tw.org.roadtoadventure.service;

import tw.org.roadtoadventure.bean.UserBean;

public interface UserFriendService {
	
	public void update (UserBean userBean) throws Exception;
	
	public void createFriend (String friendId) throws Exception;

}
