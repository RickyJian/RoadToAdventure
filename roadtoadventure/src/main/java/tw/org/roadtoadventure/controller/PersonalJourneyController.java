package tw.org.roadtoadventure.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterBatchUpdateUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tw.org.roadtoadventure.bean.GroupBean;
import tw.org.roadtoadventure.bean.PersonalBean;
import tw.org.roadtoadventure.form.CreatePersonalJourneyForm;
import tw.org.roadtoadventure.service.PersonalJourneyService;
import tw.org.roadtoadventure.vo.UserAccount;

@Controller
@RequestMapping(value = "/Personal")
public class PersonalJourneyController {
	
	@Autowired
	private PersonalJourneyService personalJourneyService;
	
	private String dir = "/personal";
	private String subDir =  dir+"/journey";
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	
	private boolean isJourneyUrlCorrect(Integer journeyId) throws Exception {
		UserAccount user = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonalBean pb = new PersonalBean();
		pb.setCreateId(user.getCreateId());
		pb.setPersonalJourneyId(journeyId);
		List<PersonalBean> pbList = personalJourneyService.readByParameter(pb);
		if(pbList.size()==1) {
			return true;
		}
		return false;
	}
	
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
//	個人歷程搜尋 頁面
	@RequestMapping(value = "/ReadAll")
	public ModelAndView readAllPage() {
		ModelAndView mav = new ModelAndView(subDir+"/readAllJourney");
		JSONObject o = new JSONObject();
		try {
			JSONArray array =new JSONArray();
			for(PersonalBean pb :personalJourneyService.readAllByUserId()) {
				JSONObject arrayObj = new JSONObject();
				arrayObj.put("userId", pb.getCreateId());
//				arrayObj.put("status", gb.getStatus());
				arrayObj.put("journeyName", pb.getPersonalJourneyName());
				arrayObj.put("journeyId", pb.getPersonalJourneyId());
				arrayObj.put("beginDate", sdf.format(pb.getBeginDate()));
				arrayObj.put("endDate", sdf.format(pb.getEndDate()));
				array.add(arrayObj);
			}
			o.put("success", "1");
			o.put("personalArray", array);
			mav.addObject("personal" ,o.toString());
		}catch(Exception ex) {
			o.put("success", "0");
			o.put("message", "搜尋失敗。");
			ex.printStackTrace();

		}
		return mav;
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
//	歷程編輯
	@RequestMapping(value= "/{journeyId}/Edit" , produces = "application/json;charset=UTF-8")
	public ModelAndView journeyEdit(@PathVariable int journeyId) throws Exception {
		if(isJourneyUrlCorrect(journeyId)) {
			ModelAndView mav = new ModelAndView(subDir+"/updateJourney");
			JSONObject o = new JSONObject();
			try {
				PersonalBean personalBean =  new PersonalBean();
				personalBean.setPersonalJourneyDetailId(journeyId);
				String op = "";
				String content ="";
				JSONArray array =new JSONArray();
				for(PersonalBean pb : personalJourneyService.readDetailByParameter(personalBean)) {
					op = pb.getOverviewPolyline();
					JSONObject arrayObj = new JSONObject();
					content = pb.getPersonalJourneyContent();
					arrayObj.put("location",pb.getLocation());
					arrayObj.put("detailId", pb.getPersonalJourneyDetailId());
					array.add(arrayObj);
				}
				op =op.replaceAll("\\\\", "\\\\\\\\");
				o.put("array", array);
				o.put("success", "1");
				o.put("journeyId ", journeyId);
				o.put("content", content);
				mav.addObject("journey" ,o.toString());
				mav.addObject("overviewPolyline", op);
			}catch(Exception ex) {
				o.put("success", "0");
				o.put("message", "路程搜尋失敗。");
				ex.printStackTrace();
				
			}
			return mav;
		}
		return null;
	}	
}
