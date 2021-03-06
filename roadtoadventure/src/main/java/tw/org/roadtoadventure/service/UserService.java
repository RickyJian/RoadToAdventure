package tw.org.roadtoadventure.service;

import java.util.List;

import tw.org.roadtoadventure.bean.UserBean;
import tw.org.roadtoadventure.form.SignUpForm;

public interface UserService {

	public void signUp (SignUpForm signUpForm) throws Exception;
	
	public UserBean readUserInfo() throws Exception;
	
	public void update(UserBean userBean) throws Exception; 
	
	public void updateForVerification(String verificationCode) throws Exception;
	
	public List<UserBean> readAllUser() throws Exception;
	
	public List<UserBean> readAllFriend() throws Exception;
	
	public List<UserBean> readByParameter(UserBean userBean) throws Exception;
	
	
	
}
