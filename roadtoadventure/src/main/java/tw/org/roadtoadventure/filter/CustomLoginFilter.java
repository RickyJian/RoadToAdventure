package tw.org.roadtoadventure.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter {
	
	public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response) throws AuthenticationException {
//		登入第一個進入點，處理頁面parameter並將此傳回後端驗證處理。
		if(request.getMethod().equals("POST")){
			String username = obtainUsername(request);
			String password = obtainPassword(request);
			//這裡將原來的UsernamePasswordAuthenticationToken換成我們自訂的CustomAuthenticationToken
			CustomAuthenticationToken authRequest = new CustomAuthenticationToken(username, password);
			//這裡就將token傳到後續驗證環節了
			setDetails(request, authRequest);
			return this.getAuthenticationManager().authenticate(authRequest);
		}else{
			throw new AuthenticationServiceException("認證方法不支援："+ request.getMethod());
		}


	}
}
