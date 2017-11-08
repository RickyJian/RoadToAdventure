package tw.org.roadtoadventure.dao;

import java.util.List;

import tw.org.roadtoadventure.bean.UserBean;
import tw.org.roadtoadventure.vo.UserFriend;

public interface UserFriendDAO extends BaseDAO<UserFriend> {
	
	public List<UserFriend> readByParameter (UserBean userBean);
	
	public List<UserFriend> readAllByUesrId (String userId);

}
