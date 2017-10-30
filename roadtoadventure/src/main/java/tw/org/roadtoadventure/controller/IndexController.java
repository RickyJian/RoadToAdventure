package tw.org.roadtoadventure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Index")
public class IndexController {

	
	@RequestMapping("/Index")
	public ModelAndView index() {
		return new ModelAndView("index");
	}
}
