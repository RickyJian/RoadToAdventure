package tw.org.roadtoadventure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;
import tw.org.roadtoadventure.form.SignUpForm;

@Controller
@RequestMapping("/User")
public class UserAccountController {

	@RequestMapping("/Login")
	public ModelAndView login () {
		return new  ModelAndView("/login");
	}
	
	@RequestMapping("/SignUp")
	public ModelAndView signUp() {
		return new ModelAndView("/signUp");
	}
	
	@RequestMapping("/SignUp/Create")
	public @ResponseBody JSONObject create (SignUpForm signUpForm){
		
		return null;
	}
}
