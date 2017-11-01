package tw.org.roadtoadventure.form;

import tw.org.roadtoadventure.bean.UserBean;

public class SignUpForm extends UserBean {
	
	private String checkPassword;

	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return super.getUserId();
	}

	@Override
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		super.setUserId(userId);
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return super.getUserName();
	}

	@Override
	public void setUserName(String userName) {
		// TODO Auto-generated method stub
		super.setUserName(userName);
	}

	@Override
	public String getUserPicture() {
		// TODO Auto-generated method stub
		return super.getUserPicture();
	}

	@Override
	public void setUserPicture(String userPicture) {
		// TODO Auto-generated method stub
		super.setUserPicture(userPicture);
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return super.getEmail();
	}

	@Override
	public void setEmail(String email) {
		// TODO Auto-generated method stub
		super.setEmail(email);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return super.getPassword();
	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		super.setPassword(password);
	}

	public String getCheckPassword() {
		return checkPassword;
	}

	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}
	


}
