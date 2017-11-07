package tw.org.roadtoadventure.service;

import tw.org.roadtoadventure.bean.UserBean;
import tw.org.roadtoadventure.form.SignUpForm;

public interface UserService {

	public void signUp (SignUpForm signUpForm) throws Exception;
	
	public UserBean readUserInfo() throws Exception;
	
	public void update(UserBean userBean) throws Exception; 
}
