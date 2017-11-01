package tw.org.roadtoadventure.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtility {
	public static boolean isPasswordQualified (String password , String checkPassword) {
		if(password!=null&&!password.equals("")&&password.equals(checkPassword)) {
			return true;
		}else {
			return false;
		}
	}
	public static String passwordHash(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes());
		byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
		return sb.toString().toUpperCase();
	}
}
