package com.his.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class PasswordUtil {
	private static final String key = "aesEncryptionKey";
	private static final String initVector = "encryptionIntVec";
	
	public static String encrypt(String pwd) {
	    try {
	        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
	        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
	        byte[] encrypted = cipher.doFinal(pwd.getBytes());
	        return Base64.getEncoder().encodeToString(encrypted);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}
	
	public static String decrypt(String encrypted) {
	    try {
	        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
	        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
	        byte[] pwd = cipher.doFinal(encrypted.getBytes());
	        return Base64.getEncoder().encodeToString(pwd);
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return null;
	}


}
