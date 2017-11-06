package tw.org.roadtoadventure.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;
import tw.org.roadtoadventure.bean.GroupBean;
import tw.org.roadtoadventure.form.SignUpForm;
import tw.org.roadtoadventure.service.UserService;
import tw.org.roadtoadventure.utils.PasswordUtility;
import tw.org.roadtoadventure.vo.UserAccount;

@Controller
@RequestMapping("/User")
public class UserAccountController {
	
	private String dir = "/setting" ;
	
	@Autowired
	private UserService userService;


	@RequestMapping("/Login")
	public ModelAndView login () {
		return new  ModelAndView("/login");
	}
	
	@RequestMapping("/SignUp")
	public ModelAndView signUp() {
		return new ModelAndView("/signUp");
	}
	
	@RequestMapping(value = "/SignUp/Create" ,  produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody String create (SignUpForm signUpForm){
		JSONObject o = new JSONObject();
		try {
			userService.signUp(signUpForm);
			PasswordUtility.passwordHash(signUpForm.getPassword());
			o.put("success", "1");
			return o.toString();
		}catch(Exception ex) {
			o.put("success", "0");
			ex.printStackTrace();
			return o.toString();
		}
	}
	
	@RequestMapping(value = "/Setting" ,  produces = "application/json;charset=UTF-8")
	public ModelAndView settingPage () {
		return new ModelAndView(dir + "/index");
	}
	@RequestMapping(value = "/Edit" ,  produces = "application/json;charset=UTF-8")
	public ModelAndView editPage () {
		return new ModelAndView(dir + "/update");
	}
}
