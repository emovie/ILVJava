package com.hello.secure;

import java.security.MessageDigest;

public class SHA256 {

	public String encrypt(String str) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(str.getBytes());
		return bytesToHex(md.digest());
	}
	
	private String bytesToHex(byte[] bytes) throws Exception {
		StringBuilder builder = new StringBuilder();
		for(byte b : bytes) {
			builder.append(String.format("%02x", b));
		}
		return builder.toString();
	}
	
}
