package tw.org.roadtoadventure.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RouteController {

	private int getErrorCode(HttpServletRequest httpRequest) {
		return (Integer) httpRequest
				.getAttribute("javax.servlet.error.status_code");
	}

	@RequestMapping("/")
	public ModelAndView index() {
		return new ModelAndView("index");
	}
	@RequestMapping(value = "/error")
	public ModelAndView errorPage(HttpServletRequest httpRequest) {
		ModelAndView errorPage = new ModelAndView("error");
		String errorMsg = "";
		int httpErrorCode = getErrorCode(httpRequest);
		switch (httpErrorCode) {
		case 400: {
			errorMsg = "Http Error Code: 400. Bad Request";
			break;
		}
		case 401: {
			errorMsg = "Http Error Code: 401. Unauthorized";
			break;
		}
		case 404: {
			errorMsg = "Http Error Code: 404. Resource not found";
			break;
		}
		case 500: {
			errorMsg = "Http Error Code: 500. Internal Server Error";
			break;
		}
		}
		errorPage.addObject("message", errorMsg);
		return errorPage;
	}

	@RequestMapping(value = "/Timeout")
	public ModelAndView timeoutPage() {
		return new ModelAndView("timeout");
	}
	@RequestMapping(value = "/Accessdeny")
	public ModelAndView accessdenyPage() {
		return new ModelAndView("accessdeny");
	}
	@RequestMapping("/Login")
	public ModelAndView login () {
		return new  ModelAndView("/login");
	}

	@RequestMapping("/SignUp")
	public ModelAndView signUp() {
		return new ModelAndView("/signUp");
	}

}
