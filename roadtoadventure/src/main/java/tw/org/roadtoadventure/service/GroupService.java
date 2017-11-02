package tw.org.roadtoadventure.service;

import java.util.List;

import tw.org.roadtoadventure.bean.GroupBean;
import tw.org.roadtoadventure.form.CreateGroupForm;

public interface GroupService {

	public void create(CreateGroupForm createGroupForm) throws Exception;
	
	public List<GroupBean> readAll() throws Exception;
	
}
