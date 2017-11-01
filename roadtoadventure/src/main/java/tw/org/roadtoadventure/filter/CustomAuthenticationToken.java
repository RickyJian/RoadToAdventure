package tw.org.roadtoadventure.filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomAuthenticationToken extends
        UsernamePasswordAuthenticationToken {

    public CustomAuthenticationToken(String principal, String credentials) {
        super(principal, credentials);
    }

}