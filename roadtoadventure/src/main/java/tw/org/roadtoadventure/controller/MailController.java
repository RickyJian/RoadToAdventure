package tw.org.roadtoadventure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSendException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;
import tw.org.roadtoadventure.service.UserService;
import tw.org.roadtoadventure.utils.MailUtility;
import tw.org.roadtoadventure.vo.UserAccount;

@Controller
@RequestMapping("/Mail")
public class MailController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/Send"  ,  produces = "application/json;charset=UTF-8")
	public @ResponseBody String send () throws Exception {
		JSONObject o = new JSONObject();
		try{
			UserAccount user = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String verificationCode = MailUtility.generateVerificationCode();
			//        	獲取applicationContext.xml
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			//          獲取applicationContext 的 Bean
			MailUtility mailer = (MailUtility) context.getBean("mailSend");
			//			寄信
			String from = "ricky03j@gmail.com";//寄件人
			String to = user.getEmail();
			String subject="揪愛騎RoadToAdventure會員註冊成功通知信";
			String msg =MailUtility.contentForVerification(verificationCode);
			mailer.sendMail(from,to,subject,msg);
			userService.updateForVerification(verificationCode);
			o.put("success", "1");
			return o.toString();
		}catch (MailSendException ex) {
			o.put("success", "0");
			return  o.toString();
		}
	}
	
}
