package br.com.weecode.weesign_backend.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

public class Utils {

	public static String generateSHA512Algorithm(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest digest = MessageDigest.getInstance("SHA-512");
		digest.reset();
		digest.update(input.getBytes("utf-8"));
		return String.format("%0128x", new BigInteger(1, digest.digest()));		
	}
	
	public static String getBaseURl(HttpServletRequest request) {
	    String scheme = request.getScheme();
	    String serverName = request.getServerName();
	    int serverPort = request.getServerPort();
	    String contextPath = request.getContextPath();
	    StringBuffer url =  new StringBuffer();
	    url.append(scheme).append("://").append(serverName);
	    if ((serverPort != 80) && (serverPort != 443)) {
	        url.append(":").append(serverPort);
	    }
	    url.append(contextPath);
	    if(url.toString().endsWith("/")){
	    	url.append("/");
	    }
	    return url.toString();
	}
}
