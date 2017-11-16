package tw.org.roadtoadventure.filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import tw.org.roadtoadventure.dao.UserAccountDAO;
import tw.org.roadtoadventure.utils.MailUtility;
import tw.org.roadtoadventure.vo.UserAccount;


public class CustomLoginHandler extends
SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private UserAccountDAO userAccountDAO;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}
	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, 
			HttpServletResponse response, Authentication authentication)
					throws IOException {
		//登入第四個進入點，登入成功後如想額外處理其他事物加在此處。
		handle(request, response, authentication);
	}

	protected void handle(HttpServletRequest request, 
			HttpServletResponse response, Authentication authentication)
					throws IOException {
		String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {

			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(Authentication authentication) {
		UserAccount user = (UserAccount)authentication.getPrincipal();
		if (user.getIsVerification()=='Y') {
			return "/";
		} else{
			try{

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
				user.setVerificationCode(verificationCode);
				userAccountDAO.merge(user);
				return "/Verification";
			}catch (MailSendException ex) {
				return "/timeoutPage";
			}

		}

	}

}