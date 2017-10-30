package tw.com.gbCustomerQuery.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter {

	private String customerNo ;
	private String email;

	public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response) throws AuthenticationException {
//		登入第一個進入點，處理頁面parameter並將此傳回後端驗證處理。
		if(request.getMethod().equals("POST")){
			String username = obtainUsername(request);
			String password = new Md5PasswordEncoder().encodePassword(obtainPassword(request), null);//密碼加密
			String email = obtainEmail(request);
			String customerNo = obtainCustomerNo(request);
			//這裡將原來的UsernamePasswordAuthenticationToken換成我們自訂的CustomAuthenticationToken
			CustomAuthenticationToken authRequest = new CustomAuthenticationToken(username, password, customerNo, email);
			//這裡就將token傳到後續驗證環節了
			setDetails(request, authRequest);
			return this.getAuthenticationManager().authenticate(authRequest);
		}else{
			throw new AuthenticationServiceException("認證方法不支援："+ request.getMethod());
		}


	}

	protected String obtainEmail(HttpServletRequest request) {
		this.email = request.getParameter("email");
		return this.email;
	}
	protected String obtainCustomerNo(HttpServletRequest request) {
		this.customerNo = request.getParameter("customerNo");
		return this.customerNo;
	}


}
