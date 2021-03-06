package tw.org.roadtoadventure.service;

import java.util.List;

import tw.org.roadtoadventure.bean.GroupBean;
import tw.org.roadtoadventure.form.CreateGroupForm;

public interface GroupService {

	public void create(GroupBean groupBean) throws Exception;
	
	public List<GroupBean> readAll() throws Exception;
	
	public void update(GroupBean groupBean) throws Exception;
	
	public GroupBean readByGroupId(Integer groupId) throws Exception;
	
}
