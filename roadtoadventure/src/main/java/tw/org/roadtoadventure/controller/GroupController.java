package tw.org.roadtoadventure.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import tw.org.roadtoadventure.form.UpdateGroupForm;
import tw.org.roadtoadventure.form.UpdateGroupJourneyForm;
import tw.org.roadtoadventure.service.GroupJourneyService;
import tw.org.roadtoadventure.service.GroupService;
import tw.org.roadtoadventure.service.UserInGroupService;
import tw.org.roadtoadventure.utils.BeanUtility;
import tw.org.roadtoadventure.utils.PasswordUtility;
import tw.org.roadtoadventure.vo.UserAccount;

@Controller
@RequestMapping("/Group")
public class GroupController {
	@Autowired
	private GroupService groupService;
	@Autowired
	private UserInGroupService userInGroupService;
	@Autowired
	private GroupJourneyService groupJourneyService;

	private String dir = "/group";
	private String subDir =  dir+"/journey";
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

	private boolean isGroupUrlCorrect(Integer pathValue) throws Exception {
		UserAccount user = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GroupBean groupBean = new GroupBean(pathValue);
		groupBean.setUserId(user.getUserId());
		List<GroupBean> gbList = userInGroupService.readByParameter(groupBean);
		if(gbList.size()==1) {
			if(gbList.get(0).getStatus()=='1') {
				return true;
			}
		}
		return false;
	}
	private boolean isJourneyUrlCorrect(Integer groupId , Integer journeyId) throws Exception {
		UserAccount user = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GroupBean groupBean = new GroupBean(groupId,journeyId);
		groupBean.setUserId(user.getUserId());
		List<GroupBean> gbList = groupJourneyService.readByParameter(groupBean);
		if(gbList.size()==1) {
			return true;
		}
		return false;
	}

	//	車隊首頁
	@PreAuthorize("hasAnyRole('admin','G00')")
	@RequestMapping("/Group")
	public ModelAndView groupIndexPage() {
		return new ModelAndView(dir+"/index");
	}
	//	車隊新增頁面
	@PreAuthorize("hasAnyRole('admin','G01')")
	@RequestMapping("/New")
	public ModelAndView newGroupPage() {
		return new ModelAndView(dir+"/createGroup");
	}

	//	個人車隊讀取
	@PreAuthorize("hasAnyRole('admin','G03')")
	@RequestMapping(value= "/Read" , produces = "application/json;charset=UTF-8")
	public ModelAndView groupReadPage() {
		ModelAndView mav = new ModelAndView(dir+"/readGroup");
		JSONObject o = new JSONObject();
		try {
			JSONArray array =new JSONArray();
			for(GroupBean gb :userInGroupService.readAllByUserId()) {
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
	@PreAuthorize("hasAnyRole('admin','G04')")
	@RequestMapping(value= "/{groupId}/Edit" , produces = "application/json;charset=UTF-8")
	public ModelAndView groupEditPage(@PathVariable Integer groupId) throws Exception {
		if(isGroupUrlCorrect(groupId)) {
			ModelAndView mav = new ModelAndView(dir+"/updateGroup");
			JSONObject o = new JSONObject();
			try {
				GroupBean gb =groupService.readByGroupId(groupId);
				o.put("userId", gb.getUserId());
				o.put("groupId", gb.getGroupId());
				o.put("status", String.valueOf(gb.getStatus()));
				o.put("groupDescription", gb.getGroupDescription());
				o.put("groupName", gb.getGroupName());
				o.put("groupPicture", gb.getGroupPicture());
				o.put("success", "1");
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
//	@PreAuthorize("hasAnyRole('admin','G34')")
	@RequestMapping(value= "/{groupId}/Update" , produces = "application/json;charset=UTF-8")
	public @ResponseBody String groupUpdate(@PathVariable Integer groupId,UpdateGroupForm updateGroupForm) throws Exception {
		if(isGroupUrlCorrect(groupId)) {
			JSONObject o = new JSONObject();
			try {
				groupService.update(updateGroupForm);;
				o.put("success", "1");
				return o.toString();
			}catch(Exception ex) {
				o.put("success", "0");
				o.put("message", "搜尋失敗。");
				ex.printStackTrace();
				return o.toString();
			}
		}
		return null;

	}
	//	團隊搜尋
//	@PreAuthorize("hasAnyRole('admin','G12')")
	@RequestMapping(value= "/ReadByParameter" , produces = "application/json;charset=UTF-8")
	public @ResponseBody String readByParameter(GroupBean groupBean) {
		UserAccount uer  = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONObject o = new JSONObject();
		try {
			JSONArray array =new JSONArray();
			groupBean.setSearchType("like");
			groupBean.setUserId(uer.getUserId());
			List<GroupBean> gbList = userInGroupService.readByParameter(groupBean);
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
//	@PreAuthorize("hasAnyRole('admin','G14')")
	@RequestMapping(value= "{groupId}/User/ReadAll" , produces = "application/json;charset=UTF-8")
	public @ResponseBody String readAllMember(@PathVariable int groupId,GroupBean groupBean) throws Exception {
		if(isGroupUrlCorrect(groupId)) {
			JSONObject o = new JSONObject();
			try {
				JSONArray array =new JSONArray();
				List<GroupBean> gbList = userInGroupService.readByParameter(new GroupBean(groupId));
				if(gbList.size()>0) {
					for(GroupBean gb :gbList) {
						JSONObject arrayObj = new JSONObject();
						arrayObj.put("userId", gb.getUserId());
						arrayObj.put("userName", gb.getUserName());
						arrayObj.put("userPicture", gb.getUserPicture());
						arrayObj.put("status", String.valueOf(gb.getStatus()));
						array.add(arrayObj);
					}
					o.put("success", "1");
					o.put("userArray", array);
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
		return null;
	}
//	@PreAuthorize("hasAnyRole('admin','G35')")
	@RequestMapping(value= "{groupId}/Update/Friend/Accept" , produces = "application/json;charset=UTF-8")
	public @ResponseBody String updateFriendAccept(@PathVariable int groupId,@RequestParam String userId) throws Exception {
		if(isGroupUrlCorrect(groupId)) {
			JSONObject o = new JSONObject();
			try {
				GroupBean gb = new GroupBean();
				gb.setStatus('1');
				gb.setGroupId(groupId);
				gb.setUserId(userId);
				userInGroupService.update(gb);
				o.put("success", "1");
				return o.toString();
			}catch(Exception ex) {
				o.put("success", "0");
				ex.printStackTrace();
				return o.toString();
			}

		}
		return null;
	}
//	@PreAuthorize("hasAnyRole('admin','G45')")
	@RequestMapping(value= "{groupId}/Update/Friend/Delete" , produces = "application/json;charset=UTF-8")
	public @ResponseBody String updateFriendDelete(@PathVariable int groupId,@RequestParam String userId) throws Exception {
		if(isGroupUrlCorrect(groupId)) {
			JSONObject o = new JSONObject();
			try {
				userInGroupService.delete(userId , groupId);
				o.put("success", "1");
				return o.toString();
			}catch(Exception ex) {
				o.put("success", "0");
				ex.printStackTrace();
				return o.toString();
			}
			
		}
		return null;
	}
	//	新增加入車隊
//	@PreAuthorize("hasAnyRole('admin','G22')")
	@RequestMapping(value = "/Create/Join")
	public @ResponseBody String join(@RequestParam int groupId) {
		JSONObject o = new JSONObject();
		try {
			GroupBean gb = new GroupBean();
			gb.setStatus('0');
			gb.setGroupId(groupId);
			gb.setGroupRoleId("GR2");
			userInGroupService.create(gb);
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
//	@PreAuthorize("hasAnyRole('admin','G21')")
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
	@PreAuthorize("hasAnyRole('admin','G02')")
	@RequestMapping(value= "/ReadAll" , produces = "application/json;charset=UTF-8")
	public ModelAndView groupReadAllPage() {
		UserAccount user = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView mav = new ModelAndView(dir+"/readAllGroup");
		JSONObject o = new JSONObject();
		try {
			List<GroupBean> gbList =  groupService.readAll();
			List<GroupBean> userList = userInGroupService.readAllByUserId();
			Map <Integer , GroupBean>  map = new TreeMap<>();
			for(GroupBean gb : userList) {
				map.put(gb.getGroupId(), gb);
			}
			JSONArray array =new JSONArray();
			for(GroupBean gb :gbList) {
				if(!map.containsKey(gb.getGroupId())) {
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
			mav.addObject("group" ,o.toString());
		}catch(Exception ex) {
			o.put("success", "0");
			o.put("message", "搜尋失敗。");
			ex.printStackTrace();

		}
		return mav;
	}

	//	車隊歷程頁面
	@PreAuthorize("hasAnyRole('admin','G07')")
	@RequestMapping(value = "/{id}/Journey" , produces = "application/json;charset=UTF-8")
	public ModelAndView jorneyIndexPage(@PathVariable int id ) throws Exception {
		if(isGroupUrlCorrect(id)) {
			return new ModelAndView(subDir+"/index","groupId",id);
		}
		return null;
	}

	//	歷程 新增頁面
	@PreAuthorize("hasAnyRole('admin','G08')")
	@RequestMapping("/{id}/Journey/New")
	public ModelAndView newJourneyPage(@PathVariable int id ) throws Exception {
		if(isGroupUrlCorrect(id)) {
			return new ModelAndView(subDir+"/createJourney","groupId",id);
		}
		return null;
	}

	//	新增歷程
//	@PreAuthorize("hasAnyRole('admin','G28')")
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
	@PreAuthorize("hasAnyRole('admin','G09')")
	@RequestMapping(value= "/{id}/Journey/ReadAll" , produces = "application/json;charset=UTF-8")
	public ModelAndView groupJourneyPage(@PathVariable int id) throws Exception {
		if(isGroupUrlCorrect(id)) {
			ModelAndView mav = new ModelAndView(subDir+"/readAllJourney");
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
	//	歷程編輯
	@PreAuthorize("hasAnyRole('admin','G010')")
	@RequestMapping(value= "/{groupId}/Journey/{journeyId}/Edit" , produces = "application/json;charset=UTF-8")
	public ModelAndView journeyEdit(@PathVariable int groupId , @PathVariable int journeyId) throws Exception {
		if(isJourneyUrlCorrect(groupId,journeyId)) {
			ModelAndView mav = new ModelAndView(subDir+"/updateJourney");
			JSONObject o = new JSONObject();
			try {
				GroupBean groupBean =  new GroupBean(groupId,journeyId);
				String op = "";
				String content ="";
				JSONArray array =new JSONArray();
				for(GroupBean gb : groupJourneyService.readDetailByParameter(groupBean)) {
					op = gb.getOverviewPolyline();
					JSONObject arrayObj = new JSONObject();
					content = gb.getGroupJourneyContent();
					arrayObj.put("location",gb.getLocation());
					arrayObj.put("detailId", gb.getGroupJourneyDetailId());
					array.add(arrayObj);
				}
				op =op.replaceAll("\\\\", "\\\\\\\\");
				o.put("array", array);
				o.put("success", "1");
				o.put("groupId ", groupId);
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
	//	歷程詳情
//	@PreAuthorize("hasAnyRole('admin','G19')")
	@RequestMapping(value= "/{groupId}/Journey/{journeyId}/Read" , produces = "application/json;charset=UTF-8")
	public ModelAndView journeyReadPage(@PathVariable int groupId , @PathVariable int journeyId) throws Exception {
		if(isJourneyUrlCorrect(groupId,journeyId)) {
			ModelAndView mav = new ModelAndView(subDir+"/readJourney");
			JSONObject o = new JSONObject();
			try {
				GroupBean groupBean =  new GroupBean(groupId,journeyId);
				String op = "";
				String content ="";
				JSONArray array =new JSONArray();
				for(GroupBean gb : groupJourneyService.readDetailByParameter(groupBean)) {
					op = gb.getOverviewPolyline();
					JSONObject arrayObj = new JSONObject();
					content = gb.getGroupJourneyContent();
					arrayObj.put("location",gb.getLocation());
					arrayObj.put("detailId", gb.getGroupJourneyDetailId());
					//					arrayObj.put("overviewPolyline", gb.getOverviewPolyline());
					//					arrayObj.put("beginDate", sdf.format(gb.getBeginDate()));
					//					arrayObj.put("endDate", sdf.format(gb.getEndDate()));
					array.add(arrayObj);
				}
				op =op.replaceAll("\\\\", "\\\\\\\\");
				o.put("array", array);
				o.put("success", "1");
				o.put("groupId ", groupId);
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
	//	歷程編輯  修改
//	@PreAuthorize("hasAnyRole('admin','G310')")
	@RequestMapping(value= "/{groupId}/Journey/{journeyId}/Update" , produces = "application/json;charset=UTF-8")
	public @ResponseBody String journeyUpdate(@PathVariable int groupId , @PathVariable int journeyId ,UpdateGroupJourneyForm updateGroupJourneyForm) throws Exception {
		if(isJourneyUrlCorrect(groupId,journeyId)) {
			JSONObject o = new JSONObject();
			try {
				GroupBean groupBean = new GroupBean();
				BeanUtility.copyProperties(updateGroupJourneyForm, groupBean);
				groupBean.setGroupId(groupId);
				groupBean.setGroupJourneyId(journeyId);
				String [] locationArray = new String [updateGroupJourneyForm.getLocationArrayStr().split(",").length];
				for(int i = 0 ; i < updateGroupJourneyForm.getLocationArrayStr().split(",").length ; i ++) {
					locationArray[i] = updateGroupJourneyForm.getLocationArrayStr().split(",")[i];
				}
				groupBean.setLocationArray(locationArray);
				groupJourneyService.update(groupBean);
				o.put("success", "1");
				o.put("groupId ",groupId);
				o.put("journeyId ", journeyId);
				return o.toString();
			}catch(Exception ex) {
				o.put("success", "0");
				o.put("message", "更新失敗。");
				ex.printStackTrace();
				return o.toString();
			}
		}
		return null;

	}
}
