package tw.org.roadtoadventure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;
import tw.org.roadtoadventure.form.SignUpForm;
import tw.org.roadtoadventure.service.UserService;
import tw.org.roadtoadventure.utils.PasswordUtility;

@Controller
@RequestMapping("/User")
public class UserAccountController {
	
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
	
	@RequestMapping("/SignUp/Create")
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
}
