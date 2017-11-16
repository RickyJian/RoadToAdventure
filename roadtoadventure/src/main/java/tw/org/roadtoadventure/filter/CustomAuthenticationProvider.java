package tw.org.roadtoadventure.filter;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tw.org.roadtoadventure.dao.UserAccountDAO;
import tw.org.roadtoadventure.dao.UserInGroupDAO;
import tw.org.roadtoadventure.utils.PasswordUtility;
import tw.org.roadtoadventure.vo.Authority;
import tw.org.roadtoadventure.vo.UserAccount;
import tw.org.roadtoadventure.vo.UserInGroup;



public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private UserAccountDAO userAccountDAO;
	@Autowired
	private UserInGroupDAO userInGroupDAO;
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
					throws AuthenticationException {
		//登入第三個進入點，如果想做點額外的檢查,可以在這個方法裡處理,校驗不通時,直接拋異常即可
	}

	@Override
	protected UserDetails retrieveUser(String username,UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		//		登入第二個進入點，由tw.com.SpringSecurity.filter.CustomLoginFilter傳來，處理登入問題。
		//    	登入邏輯
		//		User API
		//  http://docs.spring.io/spring-security/site/docs/current/apidocs/org/springframework/security/core/userdetails/User.html
		CustomAuthenticationToken cat =  ((CustomAuthenticationToken) authentication);

		String password="";
		try {
			password = PasswordUtility.passwordHash((String)cat.getCredentials());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String userId = (String)cat.getPrincipal();
		UserAccount  user= userAccountDAO.readUserForLogin(userId, password);
		if(user!=null){
			if(user.getIsEnabled()=='Y'){
				List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
				if(user.getIsVerification()=='Y'){
					JSONObject o = new JSONObject();
					Set<String> authorityTreeSet = new TreeSet<>();
					if(user.getUserRole().getUserRoleId().equals("UR0")) {
						grantedAuthorities.add(new GrantedAuthorityImpl("admin"));
					}else {
						Set<Authority> authoritySet = user.getUserRole().getAuthorities();
						for(Authority a : authoritySet) {
							authorityTreeSet.add(a.getAuthorityId());
							grantedAuthorities.add(new GrantedAuthorityImpl(a.getAuthorityId()));
						}
					}
					o.put("personal", authorityTreeSet.toString());
					List<UserInGroup> uigList = userInGroupDAO.readByUserId(userId);
					JSONArray array =new JSONArray();
					for(UserInGroup uig : uigList) {
						JSONObject arrayObj = new JSONObject();
						arrayObj.put("groupRoleId",uig.getGroupRole().getGroupRoleId());
						arrayObj.put("groupId",uig.getGroup().getGroupId());
						array.add(arrayObj);
					}
					o.put("group", array);
					user.setAuthoritiesJSON(o);
					user.setAuthorities(grantedAuthorities);
				}else{
					//throw new AuthenticationServiceException("帳戶尚未驗證成功。");
				}
				return user;
			}else{
				throw new AuthenticationServiceException("帳戶已停用。");
			}
		}
		throw new AuthenticationServiceException("帳號或密碼是否有錯誤。");


	}

}
