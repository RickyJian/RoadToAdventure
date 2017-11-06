package tw.org.roadtoadventure.controller;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;
import tw.org.roadtoadventure.form.CreatePersonalJourneyForm;
import tw.org.roadtoadventure.service.PersonalJourneyService;

@Controller
@RequestMapping(value = "/Personal")
public class PersonalJourneyController {
	
	@Autowired
	private PersonalJourneyService personalJourneyService;
	
	private String dir = "/personal";
	private String subDir =  dir+"/journey";
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	
//	個人歷程首頁
	@RequestMapping(value = "/Personal")
	public ModelAndView indexPage() {
		return new ModelAndView(dir+"/index");
	}
	
//	個人歷程新增 頁面
	@RequestMapping(value = "/New")
	public ModelAndView newPage() {
		return new ModelAndView(subDir+"/createJourney");
	}
//  新增歷程
	@RequestMapping(value = "/Create")
	public @ResponseBody String createGroup(CreatePersonalJourneyForm createPersonalJourneyForm) {
		JSONObject o = new JSONObject();
		try {
			personalJourneyService.create(createPersonalJourneyForm);
			o.put("success", "1");
			return o.toString();
		}catch(Exception ex) {
			o.put("success", "0");
			ex.printStackTrace();
			return o.toString();
		}
	}
}
