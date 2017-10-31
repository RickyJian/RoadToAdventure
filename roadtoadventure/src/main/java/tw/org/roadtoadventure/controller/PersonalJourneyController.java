package tw.org.roadtoadventure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/PersonalJourney")
public class PersonalController {

	@RequestMapping(value = "/New")
	public ModelAndView loadNewPage() {
		return new ModelAndView("personal/createJourney");
	}
}
