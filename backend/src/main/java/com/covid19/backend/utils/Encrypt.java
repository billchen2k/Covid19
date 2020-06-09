package com.covid19.backend.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密用的工具类
 *
 * @author billchen
 * @version 1.0
 * @create 2020-02-24 11:07
 **/
public class Encrypt {

	/**
	 * 输入一个字符串，单向加密成 SHA1 密码
	 * @param raw 源字符串
	 * @return SHA1 加密后的密码
	 */
	public static final String SHA1(String raw) {
		try {
			// getInstance() method is called with algorithm SHA-1
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			// digest() method is called
			// to calculate message digest of the input string
			// returned as array of byte
			byte[] messageDigest = md.digest(raw.getBytes());
			// Convert byte array into signum representation
			BigInteger no = new BigInteger(1, messageDigest);
			// Convert message digest into hex value
			String hashtext = no.toString(16);
			// Add preceding 0s to make it 32 bit
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			// return the HashText
			return hashtext;
		}
		// For specifying wrong message digest algorithms
		catch (
				NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}
