package tw.org.roadtoadventure.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tw.org.roadtoadventure.bean.UserBean;
import tw.org.roadtoadventure.dao.UserAccountDAO;
import tw.org.roadtoadventure.form.SignUpForm;
import tw.org.roadtoadventure.service.UserService;
import tw.org.roadtoadventure.utils.BeanUtility;
import tw.org.roadtoadventure.utils.PasswordUtility;
import tw.org.roadtoadventure.vo.UserAccount;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserAccountDAO userAccountDAO;
	
//	註冊
	@Override
	public void signUp(SignUpForm signUpForm) throws Exception {
		if(signUpForm!=null) {
			if(PasswordUtility.isPasswordQualified(signUpForm.getPassword(), signUpForm.getCheckPassword())) {
				UserAccount ua = new UserAccount();
				BeanUtility.copyProperties(signUpForm, ua);
				ua.setCreateDate(new Date());
				ua.setPassword(PasswordUtility.passwordHash(signUpForm.getPassword()));
				ua.setCreateId("System");
				ua.setIsEnabled('Y');
				ua.setIsVerification('N');
				ua.setUserRoleId("1");
				userAccountDAO.create(ua);
			}else {
				throw new Exception("確認密碼失敗。");
			}
		}
	}

	@Override
	public UserBean readUserInfo() throws Exception {
		UserAccount user = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserAccount result = userAccountDAO.getById(user.getUserId());
		UserBean ub =  new UserBean();
		BeanUtility.copyProperties(result, ub);
		return ub;
	}

	@Override
	public void update(UserBean userBean) throws Exception {
		UserAccount user = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserAccount result = userAccountDAO.getById(user.getUserId());
		result.setEmail(userBean.getEmail());
		result.setUserName(userBean.getUserName());
		result.setUserPicture(userBean.getUserPicture());
		userAccountDAO.merge(result);
	}

	@Override
	public List<UserBean> readAllUser() throws Exception {
		List<UserAccount> userList = userAccountDAO.readAll();
		List<UserBean> userBeanList= new ArrayList<>();
		for(UserAccount uer : userList) {
			uer.setUserId(uer.getUserId());
			UserBean userBean = new UserBean();
			BeanUtility.copyProperties(uer, userBean);
			userBeanList.add(userBean);
		}
		return userBeanList;
	}

	@Override
	public List<UserBean> readAllFriend() throws Exception {
		List<UserAccount> userList = userAccountDAO.readAll();
		List<UserBean> userBeanList= new ArrayList<>();
		for(UserAccount uer : userList) {
			UserBean userBean = new UserBean();
			BeanUtility.copyProperties(uer, userBean);
			userBeanList.add(userBean);
		}
		return userBeanList;
	}

	@Override
	public List<UserBean> readByParameter(UserBean userBean) throws Exception {
		List<UserAccount> userList = userAccountDAO.readByParameter(userBean);
		List<UserBean> userBeanList= new ArrayList<>();
		for(UserAccount user : userList) {
			UserBean ub = new UserBean();
			BeanUtility.copyProperties(user, ub);
			ub.setUserId(user.getUserId());
			userBeanList.add(ub);
		}
		return userBeanList;
	}

	
	
}
