/**
 * 영주가 넣음
 */
package kr.or.ddit.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SecurityUtils {
	public static String encryptSha512(String plain){
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] encrypted = md.digest(plain.getBytes());
			String encoded = Base64.getEncoder().encodeToString(encrypted);
			return encoded;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
}
