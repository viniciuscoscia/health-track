package br.com.healthtrack.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CriptografiaUtils {
	
	public static String criptografar(String senha) {
		MessageDigest md;
		BigInteger hash = null;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(senha.getBytes("UTF-8"));
			hash = new BigInteger(1, md.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return hash.toString(16);
	}
	
}
