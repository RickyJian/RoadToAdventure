package tw.org.roadtoadventure.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tw.org.roadtoadventure.bean.GroupBean;
import tw.org.roadtoadventure.bean.UserBean;
import tw.org.roadtoadventure.form.SignUpForm;
import tw.org.roadtoadventure.form.UpdateUserAccountForm;
import tw.org.roadtoadventure.service.UserFriendService;
import tw.org.roadtoadventure.service.UserService;
import tw.org.roadtoadventure.utils.PasswordUtility;
import tw.org.roadtoadventure.vo.UserAccount;

@Controller
@RequestMapping("/User")
public class UserAccountController {

	private String dir = "/setting" ;
	private String subDir = dir+"/friend" ;

	@Autowired
	private UserService userService;

	@Autowired
	private UserFriendService userFriendService;
	
//	註冊用 搜尋帳號是否註冊
	@RequestMapping(value = "/Read/IsUserIdCorrect" ,  produces = "application/json;charset=UTF-8")
	public @ResponseBody String isUserIdCorrect (@RequestParam String userId) {
		UserBean userBean = new UserBean();
		userBean.setUserId(userId);
		JSONObject o = new JSONObject();
		try {
			List<UserBean> userList =  userService.readByParameter(userBean);
			if(userList.size()==0) {
				o.put("isEmpty", true);
			}else {				
				o.put("isEmpty", false);
			}
			o.put("success", "1");
		}catch(Exception ex) {
			o.put("success", "0");
			ex.printStackTrace();
		}
		return o.toString();
	}

//	註冊功能
	@RequestMapping(value = "/SignUp/Create" ,  produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public @ResponseBody String create (SignUpForm signUpForm){
		JSONObject o = new JSONObject();
		try {
			userService.signUp(signUpForm);
			o.put("success", "1");
			return o.toString();
		}catch(Exception ex) {
			o.put("success", "0");
			ex.printStackTrace();
			return o.toString();
		}
	}
	//	管理頁面
	@PreAuthorize("hasAnyRole('admin','S03')")
	@RequestMapping(value = "/Setting" ,  produces = "application/json;charset=UTF-8")
	public ModelAndView settingPage () {
		return new ModelAndView(dir + "/index");
	}
	//	個資修改 頁面
	@PreAuthorize("hasAnyRole('admin','S04')")
	@RequestMapping(value = "/Setting/Edit" ,  produces = "application/json;charset=UTF-8")
	public ModelAndView editPage () {
		ModelAndView mav =  new ModelAndView(dir + "/update");
		JSONObject o = new JSONObject();
		try {
			UserBean user = userService.readUserInfo();
			o.put("userId", user.getUserId());
			o.put("name", user.getUserName());
			o.put("email", user.getEmail());
			o.put("success", "1");
			mav.addObject("userAccount",o.toString());
		}catch(Exception ex) {
			ex.printStackTrace();
			o.put("success", "0");
		}
		return mav;
	}
	
	//	個資修改 修改功能
	@PreAuthorize("hasAnyRole('admin','S34')")
	@RequestMapping(value = "/Setting/Edit/Update" ,  produces = "application/json;charset=UTF-8")
	public @ResponseBody String update (UpdateUserAccountForm updateUserAccountForm) {
		JSONObject o = new JSONObject();
		try {
			userService.update(updateUserAccountForm);
			o.put("success", "1");
		}catch(Exception ex) {
			ex.printStackTrace();
			o.put("success", "0");
		}
		return o.toString();	
	}
	
	//	好友系統 頁面
	@PreAuthorize("hasAnyRole('admin','S05')")
	@RequestMapping(value = "/Setting/Friend" ,  produces = "application/json;charset=UTF-8")
	public ModelAndView friendIndexPage () {
		return new ModelAndView(subDir + "/index");
	}
	
//	 帳戶搜尋 頁面
	@PreAuthorize("hasAnyRole('admin','S07')")
	@RequestMapping(value = "/Setting/Friend/ReadAllUser" ,  produces = "application/json;charset=UTF-8")
	public ModelAndView readAllUserPage () {
		UserAccount  user= (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView mav = new ModelAndView(dir + "/readAllUser");
		JSONObject o = new JSONObject();
		try {
			List<UserBean> ubList =  userService.readAllUser();
			JSONArray array = new JSONArray();
			for(UserBean userBean :ubList) {
				if(!user.getUserId().equals(userBean.getUserId())) {
					JSONObject arrayObj = new JSONObject();
					arrayObj.put("picture", userBean.getUserPicture()==null?"/RoadToAdventure/assets/images/p1.png":userBean.getUserPicture());
					arrayObj.put("name", userBean.getUserName());
					arrayObj.put("userId", userBean.getUserId());
					arrayObj.put("email", userBean.getEmail());
					array.add(arrayObj);

				}
			}
			o.put("array", array);
			o.put("success", "1");
			mav.addObject("account" ,o.toString() );			
		}catch(Exception ex) {
			o.put("success", "0");
			ex.printStackTrace();
		}
		return mav;
	}

	//	好友管理 頁面
	@PreAuthorize("hasAnyRole('admin','S06')")
	@RequestMapping(value = "/Setting/Friend/ReadAllFriend" ,  produces = "application/json;charset=UTF-8")
	public ModelAndView readAllFriendPage () {
		ModelAndView mav = new ModelAndView(subDir + "/readAllFriend");
		JSONObject o = new JSONObject();
		try {
			List<UserBean> ubList = userFriendService.readAllWithJoin();
			JSONArray array = new JSONArray();
			for(UserBean userBean :ubList) {
				JSONObject arrayObj = new JSONObject();
				arrayObj.put("picture", userBean.getUserPicture()==null?"/RoadToAdventure/assets/images/p1.png":userBean.getUserPicture());
				arrayObj.put("name", userBean.getUserName());
				arrayObj.put("userId", userBean.getUserId());
				arrayObj.put("friendId", userBean.getFriendId());
				arrayObj.put("status", userBean.getStatus());
				arrayObj.put("email", userBean.getEmail());
				array.add(arrayObj);
			}
			o.put("array", array);
			o.put("success", "1");
			mav.addObject("account" ,o.toString() );			
		}catch(Exception ex) {
			o.put("success", "0");
			ex.printStackTrace();
		}
		return mav;
	}

	//	帳戶搜尋 搜尋功能
	@PreAuthorize("hasAnyRole('admin','S17')")
	@RequestMapping(value= "/Setting/Account/ReadByParameter" , produces = "application/json;charset=UTF-8")
	public @ResponseBody String readAccountByParameter(UserBean userBean) {
		UserAccount  user= (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONObject o = new JSONObject();
		try {
			JSONArray array =new JSONArray();
			userBean.setSearchType("like");
			List<UserBean> ubList = userService.readByParameter(userBean);
			if(ubList.size()>0) {
				for(UserBean ub :ubList) {
					if(!ub.getUserId().equals(user.getUserId())) {
						JSONObject arrayObj = new JSONObject();
						arrayObj.put("picture", ub.getUserPicture()==null?"/RoadToAdventure/assets/images/p1.png":ub.getUserPicture());
						arrayObj.put("name", ub.getUserName());
						arrayObj.put("userId", ub.getUserId());
						arrayObj.put("email", ub.getEmail());
						array.add(arrayObj);
					}
				}
				o.put("success", "1");
				o.put("array", array);
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
	
	//	好友管理 搜尋功能
	@PreAuthorize("hasAnyRole('admin','S16')")
	@RequestMapping(value= "/Setting/Friend/ReadByParameter" , produces = "application/json;charset=UTF-8")
	public @ResponseBody String readFriendByParameter(UserBean userBean) {
		UserAccount  user= (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JSONObject o = new JSONObject();
		try {
			JSONArray array =new JSONArray();
			userBean.setSearchType("like");
			userBean.setUserId(user.getUserId());

			List<UserBean> ubList = userFriendService.readByParameter(userBean);
			if(ubList.size()>0) {
				for(UserBean ub :ubList) {
					JSONObject arrayObj = new JSONObject();
					arrayObj.put("picture", ub.getUserPicture()==null?"/RoadToAdventure/assets/images/p1.png":userBean.getUserPicture());
					arrayObj.put("name", ub.getUserName());
					arrayObj.put("userId", ub.getUserId());
					arrayObj.put("friendId", ub.getFriendId());
					arrayObj.put("status", ub.getStatus());
					arrayObj.put("email", ub.getEmail());
					array.add(arrayObj);
				}
				o.put("success", "1");
				o.put("array", array);
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
	// 帳戶搜尋	新增好友
	@PreAuthorize("hasAnyRole('admin','S27')")
	@RequestMapping(value = "/Setting/Friend/Create/Join" , produces = "application/json;charset=UTF-8")
	public @ResponseBody String join(@RequestParam String userId) {
		JSONObject o = new JSONObject();
		try {
			userFriendService.createFriend(userId);
			o.put("success", "1");
			return o.toString();
		}catch(Exception ex) {
			o.put("success", "0");
			o.put("message", "加入失敗。");
			ex.printStackTrace();
			return o.toString();
		}
	}
	//	好友管理 異動功能 接受邀請 
	@PreAuthorize("hasAnyRole('admin','S36')")
	@RequestMapping(value = "/Setting/Friend/Update/Accept" , produces = "application/json;charset=UTF-8")
	public @ResponseBody String accept(@RequestParam String userId) {
		JSONObject o = new JSONObject();
		try {
			userFriendService.updateAccept(userId);
			o.put("success", "1");
			return o.toString();
		}catch(Exception ex) {
			o.put("success", "0");
			o.put("message", "加入失敗。");
			ex.printStackTrace();
			return o.toString();
		}
	}
	// 好友管理 異動功能 拒絕邀請
	@PreAuthorize("hasAnyRole('admin','S46')")
	@RequestMapping(value = "/Setting/Friend/Delete" , produces = "application/json;charset=UTF-8")
	public @ResponseBody String deleteFriend(@RequestParam String friendId) {
		JSONObject o = new JSONObject();
		try {
			userFriendService.delete(friendId);
			o.put("success", "1");
			return o.toString();
		}catch(Exception ex) {
			o.put("success", "0");
			o.put("message", "加入失敗。");
			ex.printStackTrace();
			return o.toString();
		}
	}
	@RequestMapping(value = "/Setting/Update/Verification" , produces = "application/json;charset=UTF-8")
	public @ResponseBody String verification(@RequestParam String verificationCode) {
		JSONObject o = new JSONObject();
		try {
			UserBean u = userService.readUserInfo();
			if(u.getVerificationCode().equals(verificationCode)) {
				UserBean userBean = new UserBean();
				userBean.setIsVerification('Y');
				userService.update(userBean);				
				o.put("success", "1");
				return o.toString();
			}else {
				throw new Exception("認證失敗");
			}
		}catch(Exception ex) {
			o.put("success", "0");
			o.put("message", "認證失敗。");
			ex.printStackTrace();
			return o.toString();
		}
	}
}
