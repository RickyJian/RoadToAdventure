package tw.org.roadtoadventure.dao;

import java.util.List;

import tw.org.roadtoadventure.bean.GroupBean;
import tw.org.roadtoadventure.vo.Group;

public interface GroupDAO extends BaseDAO<Group> {

	public List<Group> readByParameter(GroupBean groupBean);
	
}
