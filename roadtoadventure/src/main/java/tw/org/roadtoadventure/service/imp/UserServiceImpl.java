package tw.org.roadtoadventure.service.imp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	
	
}
