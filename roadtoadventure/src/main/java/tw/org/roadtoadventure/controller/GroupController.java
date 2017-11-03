package tw.org.roadtoadventure.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tw.org.roadtoadventure.bean.GroupBean;
import tw.org.roadtoadventure.form.CreateGroupForm;
import tw.org.roadtoadventure.form.CreateGroupJourneyForm;
import tw.org.roadtoadventure.service.GroupJourneyService;
import tw.org.roadtoadventure.service.GroupService;
import tw.org.roadtoadventure.utils.PasswordUtility;
import tw.org.roadtoadventure.vo.UserAccount;

@Controller
@RequestMapping("/Group")
public class GroupController {

	@Autowired
	private GroupService groupService;
	@Autowired
	private GroupJourneyService groupJourneyService;

	private String dir = "/group";
	private String subDir =  dir+"/journey";
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	
	private boolean isGroupUrlCorrect(Integer pathValue) {
		UserAccount user = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GroupBean groupBean = new GroupBean(pathValue);
		groupBean.setUserId(user.getUserId());
		List<GroupBean> gbList = groupService.readByParameter(groupBean);
		if(gbList.size()==1) {
			if(gbList.get(0).getStatus()=='1') {
				return true;
			}
		}
		return false;
	}
	
	//	團隊首頁
	@RequestMapping("/Group")
	public ModelAndView groupIndex() {
		return new ModelAndView(dir+"/index");
	}
	//	車隊新增頁面
	@RequestMapping("/New")
	public ModelAndView newGroupPage() {
		return new ModelAndView(dir+"/createGroup");
	}
	
	//	個人車隊讀取
	@RequestMapping(value= "/Read" , produces = "application/json;charset=UTF-8")
	public ModelAndView groupReadPage() {
		ModelAndView mav = new ModelAndView(dir+"/readGroup");
		JSONObject o = new JSONObject();
		try {
			JSONArray array =new JSONArray();
			for(GroupBean gb :groupService.readAll()) {
				JSONObject arrayObj = new JSONObject();
				arrayObj.put("userId", gb.getUserId());
				arrayObj.put("groupId", gb.getGroupId());
				arrayObj.put("status", gb.getStatus());
				arrayObj.put("groupDescription", gb.getGroupDescription());
				arrayObj.put("groupName", gb.getGroupName());
				arrayObj.put("groupPicture", gb.getGroupPicture());
				array.add(arrayObj);
				
			}
			o.put("success", "1");
			o.put("groupArray", array);
			mav.addObject("group" ,o.toString());
		}catch(Exception ex) {
			o.put("success", "0");
			o.put("message", "搜尋失敗。");
			ex.printStackTrace();
			
		}
		return mav;
	}
	//	團隊搜尋
	@RequestMapping(value= "/ReadByParameter" , produces = "application/json;charset=UTF-8")
	public @ResponseBody String readByParameter(GroupBean groupBean) {
		JSONObject o = new JSONObject();
		try {
			JSONArray array =new JSONArray();
			groupBean.setSearchType("like");
			List<GroupBean> gbList = groupService.readByParameter(groupBean);
			if(gbList.size()>0) {
				for(GroupBean gb :gbList) {
					if(gb.getStatus()!='1') {
						JSONObject arrayObj = new JSONObject();
						arrayObj.put("userId", gb.getUserId());
						arrayObj.put("groupId", gb.getGroupId());
						arrayObj.put("groupName", gb.getGroupName());
						arrayObj.put("groupPicture", gb.getGroupPicture());
						array.add(arrayObj);
					}
				}
				o.put("success", "1");
				o.put("groupArray", array);
			}else {
				o.put("success", "0");
				o.put("message", "搜尋出0筆資料。");
			}
			return o.toString();
		}catch(Exception ex) {
			o.put("success", "0");
			o.put("message", "搜尋失敗。");
			ex.printStackTrace();
			return o.toString();
		}
	}
	//	新增加入車隊
	@RequestMapping(value = "/Create/Join")
	public @ResponseBody String join(@RequestParam int groupId) {
		JSONObject o = new JSONObject();
		try {
			GroupBean gb = new GroupBean();
			gb.setStatus('0');
			gb.setGroupId(groupId);
			gb.setGroupRoleId("2");
			groupService.update(gb);
			o.put("success", "1");
			return o.toString();
		}catch(Exception ex) {
			o.put("success", "0");
			o.put("message", "加入失敗。");
			ex.printStackTrace();
			return o.toString();
		}
	}
	
	//	新增車隊
	@RequestMapping(value = "/Create")
	public @ResponseBody String createGroup(CreateGroupForm createGroupForm) {
		JSONObject o = new JSONObject();
		try {
			groupService.create(createGroupForm);
			o.put("success", "1");
			return o.toString();
		}catch(Exception ex) {
			o.put("success", "0");
			ex.printStackTrace();
			return o.toString();
		}
	}
	
	//	車隊搜尋頁面
	@RequestMapping(value= "/ReadAll" , produces = "application/json;charset=UTF-8")
	public ModelAndView groupReadAllPage() {
		ModelAndView mav = new ModelAndView(dir+"/readAllGroup");
		JSONObject o = new JSONObject();
		try {
			JSONArray array =new JSONArray();
			for(GroupBean gb :groupService.readAll()) {
				JSONObject arrayObj = new JSONObject();
				//				自己的車隊不搜尋出來
				if(gb.getStatus()!='1') {
					arrayObj.put("userId", gb.getUserId());
					arrayObj.put("groupId", gb.getGroupId());
					arrayObj.put("groupName", gb.getGroupName());
					arrayObj.put("groupPicture", gb.getGroupPicture());
					array.add(arrayObj);
				}
			}
			o.put("success", "1");
			o.put("groupArray", array);
			mav.addObject("group" ,o.toString());
		}catch(Exception ex) {
			o.put("success", "0");
			o.put("message", "搜尋失敗。");
			ex.printStackTrace();

		}
		return mav;
	}

	//	車隊歷程頁面
	@RequestMapping(value = "/{id}/Journey" , produces = "application/json;charset=UTF-8")
	public ModelAndView jorneyIndexPage(@PathVariable int id ) {
		if(isGroupUrlCorrect(id)) {
			return new ModelAndView(subDir+"/index","groupId",id);
		}
		return null;
	}

	//	歷程 新增頁面
	@RequestMapping("/{id}/Journey/New")
	public ModelAndView newJourneyPage(@PathVariable int id ) {
		if(isGroupUrlCorrect(id)) {
			return new ModelAndView(subDir+"/createJourney","groupId",id);
		}
		return null;
	}
	
	//	新增歷程
	@RequestMapping(value = "/{groupId}/Journey/Create", produces = "application/json;charset=UTF-8")
	public @ResponseBody String createJourney(CreateGroupJourneyForm createGroupJourneyForm) {
		JSONObject o = new JSONObject();
		try {
			groupJourneyService.create(createGroupJourneyForm);
			o.put("success", "1");
			return o.toString();
		}catch(Exception ex) {
			o.put("success", "0");
			ex.printStackTrace();
			return o.toString();
		}
	}
	
//	歷程讀取
	@RequestMapping(value= "/{id}/Journey/Read" , produces = "application/json;charset=UTF-8")
	public ModelAndView groupJourneyPage(@PathVariable int id) {
		if(isGroupUrlCorrect(id)) {
			ModelAndView mav = new ModelAndView(subDir+"/readJourney");
			JSONObject o = new JSONObject();
			try {
				JSONArray array =new JSONArray();
				for(GroupBean gb :groupJourneyService.readAll()) {
					JSONObject arrayObj = new JSONObject();
					arrayObj.put("journeyName", gb.getGroupJourneyName());
					arrayObj.put("journeyId", gb.getGroupJourneyId());
					arrayObj.put("groupId", gb.getGroupId());
					arrayObj.put("beginDate", sdf.format(gb.getBeginDate()));
					arrayObj.put("endDate", sdf.format(gb.getEndDate()));
					array.add(arrayObj);
				}
				o.put("success", "1");
				o.put("groupArray", array);
				mav.addObject("group" ,o.toString());
			}catch(Exception ex) {
				o.put("success", "0");
				o.put("message", "搜尋失敗。");
				ex.printStackTrace();
				
			}
			return mav;
		}
		return null;
		
	}
}
