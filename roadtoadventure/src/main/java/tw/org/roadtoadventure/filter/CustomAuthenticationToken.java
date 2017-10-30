package tw.org.roadtoadventure.filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomAuthenticationToken extends
        UsernamePasswordAuthenticationToken {

    private String customerNo;
    private String email;
    
    public CustomAuthenticationToken(String principal, String credentials,
            String customerNo, String email) {
        super(principal, credentials);
        this.customerNo = customerNo;
        this.email = email;
    }

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}