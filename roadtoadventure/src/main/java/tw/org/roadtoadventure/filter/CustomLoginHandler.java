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


public class CustomLoginHandler extends
SavedRequestAwareAuthenticationSuccessHandler {

//	@Autowired
//	SysCustomerInforDao sysCustomerInforDao;
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
		return null;
//		SysCustomerInfor scif = (SysCustomerInfor)authentication.getPrincipal();
//
//		if (scif.getIsVerification()=='Y') {
//			Calendar c = Calendar.getInstance();
//			if(scif.getNextChangePasswordDate()!=null){
//				c.setTime(scif.getNextChangePasswordDate());
//				if(c.getTime().after(new Date())){
//
//					return "/index.jsp";
//				}else{
//					scif.setNextChangePasswordDate(c.getTime());
//					sysCustomerInforDao.merge(scif);
//					return "/changePassword.jsp";
//				}
//			}else{
//				c.add(Calendar.MONTH, 3);
//				scif.setNextChangePasswordDate(c.getTime());
//				sysCustomerInforDao.merge(scif);
//				return "/index.jsp";
//			}
//		} else{
//			try{
//				if(!scif.getMemo().equals("忘記密碼。")){
//					
//					String verificationCode = MailUtility.generateVerificationCode();
//					//        	獲取applicationContext.xml
//					ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//					//          獲取applicationContext 的 Bean
//					MailUtility mailer = (MailUtility) context.getBean("mailSend");
//					//			寄信
//					String from = "cstdorm@twgb.com.tw";//寄件人
//					String to = scif.getId().getAccountUsingEmail();
//					String subject="鎵興集團客戶查詢會員註冊成功通知信";
//					
//					String msg =MailUtility.contentForVerification(verificationCode);
//					mailer.sendMail(from,to,subject,msg);
//					Calendar c = Calendar.getInstance();
//					c.add(Calendar.HOUR, 1);
////				CustomerAndRoleIdId customerAndRoleIdId = new CustomerAndRoleIdId();
////				customerAndRoleIdId.setAccountUsingEmail(scif.getId().getAccountUsingEmail());
////				customerAndRoleIdId.setCustomerNo(scif.getCustomerNo());
////				customerAndRoleIdId.setUserName(scif.getId().getUserName());
//					SysCustomerInforId id = new SysCustomerInforId();
//					id.setAccountUsingEmail(scif.getId().getAccountUsingEmail());
//					id.setUserName(scif.getId().getUserName());
//					scif.setLastPassword(verificationCode);
//					scif.setId(id);
//					scif.setStopDate(c.getTime());
//					scif.setMemo("超過驗證時間。");
//					scif.setModifyId(scif.getId().getUserName());
//					scif.setModifyDate(new Date());
//					sysCustomerInforDao.merge(scif);
//				}
//				return "/verificationPage.jsp";
//			}catch (MailSendException ex) {
//				return "/timeoutPage.jsp?status=failed&code=0";
//			}
//
//		}

	}

}