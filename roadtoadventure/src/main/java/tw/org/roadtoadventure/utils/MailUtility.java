package tw.org.roadtoadventure.utils;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeBodyPart;

public class MailUtility {
	private JavaMailSender javaMailSender;
	
	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}



	public void sendMail(String from, String to, String subject, String msg) {
		MimeMessage message = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true,"utf-8");

			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(msg,true);
			javaMailSender.send(message);
			
		}catch (MessagingException e) {
			throw new MailParseException(e);
		}
	}
	
	public static String generateVerificationCode(){
		String[] verificationCodeArr = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G",
				"H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		String verificationCode = "";
		for(int i = 0 ; i <18 ;i++){
			Random ran = new Random();
			verificationCode+= verificationCodeArr[ran.nextInt(36)];
		}
		return  new Md5PasswordEncoder().encodePassword(verificationCode,null);
	}
	
	public static String contentForForgetPassword(String verificationCode){
		StringBuffer html = new StringBuffer();
		html.append("您好：<br/><br/>");
		html.append("由於您忘記密碼，請您在1小時內完成驗證。<br/><br/>");
		html.append("驗證碼："+verificationCode);
		html.append("<br/><br/>=====================================<br/>");
		html.append("本信件由系統發送，請勿直接回覆，謝謝！<br/>");
		html.append("=====================================");
		return html.toString();
	}
	public static String contentForVerification(String verificationCode){
		StringBuffer html = new StringBuffer();
		html.append("您好：<br/><br/>");
		html.append("會員註冊成功，請在1小時內完成驗證。<br/><br/>");
		html.append("驗證碼："+verificationCode);
		html.append("<br/><br/>=====================================<br/>");
		html.append("本信件由系統發送，請勿直接回覆，謝謝！<br/>");
		html.append("=====================================");
		return html.toString();
	}
}
