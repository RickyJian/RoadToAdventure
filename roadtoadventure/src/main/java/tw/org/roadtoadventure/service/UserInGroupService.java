package tw.org.roadtoadventure.service;

import java.util.List;

import tw.org.roadtoadventure.bean.GroupBean;

public interface UserInGroupService {

	public void create(GroupBean groupBean) throws Exception;
	
	public void update(GroupBean groupBean) throws Exception;
	
	public void delete(GroupBean groupBean) throws Exception;
	
//	public List<GroupBean> readAllWithoutUserId() throws Exception; 
	
	public List<GroupBean> readAllByUserId() throws Exception;
	
	public List<GroupBean> readAll() throws Exception;
	
	public List<GroupBean> readByParameter(GroupBean groupBean)throws Exception;
	
}
