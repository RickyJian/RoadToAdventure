package tw.org.roadtoadventure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;
import tw.org.roadtoadventure.form.CreateGroupForm;
import tw.org.roadtoadventure.service.GroupService;
import tw.org.roadtoadventure.utils.PasswordUtility;

@Controller
@RequestMapping("/Group")
public class GroupController {
	
   @Autowired
   private GroupService groupService;
	
	private String dir = "/group";
//	團隊首頁
	@RequestMapping("/Index")
	public ModelAndView index() {
		return new ModelAndView(dir+"/index");
	}
//	車隊新增頁面
	@RequestMapping("/New")
	public ModelAndView newPage() {
		return new ModelAndView(dir+"/createGroup");
	}
//	個人車隊讀取
	@RequestMapping("/Read")
	public ModelAndView readPage() {
		return new ModelAndView(dir+"/readGroup");
	}
//	車隊搜尋頁面
	@RequestMapping("/ReadAll")
	public ModelAndView readAllPage() {
		return new ModelAndView(dir+"/readAllGroup");
	}
//	新增車隊
	@RequestMapping("/Create")
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
	
}
