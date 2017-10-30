package tw.org.roadtoadventure.filter;

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

import tw.com.gbCustomerQuery.dao.SysCustomerInforDao;
import tw.com.gbCustomerQuery.dao.SysCustomerInforMappingDao;
import tw.com.gbCustomerQuery.utils.StringUtility;
import tw.com.gbCustomerQuery.vo.Authority;
import tw.com.gbCustomerQuery.vo.SysCustomerInfor;
import tw.com.gbCustomerQuery.vo.SysCustomerInforMapping;


public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	SysCustomerInforDao sysCustomerInforDao;
	@Autowired
	SysCustomerInforMappingDao sysCustomerInforMappingDao;
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

		String customerNo = cat.getCustomerNo();
		String email = cat.getEmail();
		String password = (String)cat.getCredentials();

		SysCustomerInforMapping scifm = sysCustomerInforMappingDao.getCustomerInforForLogin(username, customerNo, email);
		if(scifm!=null){

			SysCustomerInfor scif = sysCustomerInforDao.getCustomerInforForLogin(username, password, email);
			if(scif!=null&&StringUtility.stringCompare(new String []{scif.getUsername(),scifm.getId().getCustomerNo(),scif.getId().getAccountUsingEmail()}, new String []{username,customerNo,email})){
				scif.setCustomerNo(scifm.getId().getCustomerNo());
				if(scif.getIsEnable()=='Y'){
					List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
					if(scif.getIsVerification()=='Y'){
						if(scifm.getSysCustomerRole().getSysUserRole().getRoleId()==0){
							grantedAuthorities.add(new GrantedAuthorityImpl("admin"));
						}else{
							Set<Authority> authoritySet=scifm.getSysCustomerRole().getSysUserRole().getAuthorities();
							for(Authority authority:authoritySet){
								grantedAuthorities.add(new GrantedAuthorityImpl(authority.getAuthorityId()));
							}
						}
					}else{
						grantedAuthorities.add(new GrantedAuthorityImpl("S1"));
					}
					scif.setAuthorities(grantedAuthorities);
					return scif;
				}else{
					throw new AuthenticationServiceException("帳戶已停用。");
				}
			}else{
				throw new AuthenticationServiceException("請檢查客戶代號、Email、帳號或是密碼是否有錯誤。");
			}
		}
		throw new AuthenticationServiceException("請檢查客戶代號、Email、帳號或是密碼是否有錯誤。");


	}

}
