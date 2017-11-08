package tw.org.roadtoadventure.dao;

import java.util.List;

import tw.org.roadtoadventure.bean.UserBean;
import tw.org.roadtoadventure.vo.UserAccount;

public interface UserAccountDAO extends BaseDAO<UserAccount> {
	
	public UserAccount readUserForLogin(String userId , String password);

	public List<UserAccount> readByParameter(UserBean userBean);
}
