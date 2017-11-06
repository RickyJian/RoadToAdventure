package tw.org.roadtoadventure.dao;


import java.util.List;

import tw.org.roadtoadventure.bean.GroupBean;
import tw.org.roadtoadventure.vo.UserInGroup;

public interface UserInGroupDAO extends BaseDAO<UserInGroup> {

	public List<UserInGroup> readByUserId(String userId);
	
	public List<UserInGroup> readAllWithJoin();
	
	public List<UserInGroup> readByParameter(GroupBean groupBean);
	
}
