package tw.org.roadtoadventure.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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


	@RequestMapping("/Login")
	public ModelAndView login () {
		return new  ModelAndView("/login");
	}

	@RequestMapping("/SignUp")
	public ModelAndView signUp() {
		return new ModelAndView("/signUp");
	}

	@RequestMapping(value = "/SignUp/Create" ,  produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
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
	//	管理頁面
	@RequestMapping(value = "/Setting" ,  produces = "application/json;charset=UTF-8")
	public ModelAndView settingPage () {
		return new ModelAndView(dir + "/index");
	}
	//	個資修改 
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
			mav.addObject("user",o.toString());
		}catch(Exception ex) {
			ex.printStackTrace();
			o.put("success", "0");
		}
		return mav;
	}
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
	@RequestMapping(value = "/Setting/Friend" ,  produces = "application/json;charset=UTF-8")
	public ModelAndView friendIndexPage () {
		return new ModelAndView(subDir + "/index");
	}
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
			mav.addObject("user" ,o.toString() );			
		}catch(Exception ex) {
			o.put("success", "0");
			ex.printStackTrace();
		}
		return mav;
	}
	@RequestMapping(value = "/Setting/Friend/ReadAllFriend" ,  produces = "application/json;charset=UTF-8")
	public ModelAndView readAllFriendPage () {
		return new ModelAndView(subDir + "/readAllFriend");
	}

	//	帳戶搜尋
	@RequestMapping(value= "/Setting/Friend/ReadByParameter" , produces = "application/json;charset=UTF-8")
	public @ResponseBody String readByParameter(UserBean userBean) {
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
	//	新增加入車隊
	@RequestMapping(value = "/Setting/Friend/Create/Join")
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
}
