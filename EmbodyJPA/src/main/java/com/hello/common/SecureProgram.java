package com.hello.common;

import javax.persistence.NoResultException;

public class SecureProgram {

	public boolean xssFilter(String... args) throws Exception {
		String regex = "<|>|\\(|\\)|'|\"|;|\\/";
		for(String str : args) {
			String[] regexSplit = str.split(regex);
			if(regexSplit.length != 1) { return false; }
		}
		return true;
	}
	
	public boolean xssLoginFilter(String... args) throws Exception {
		String regex = "^[a-zA-Z0-9]*$";
		for(String str : args) {
			if(!str.matches(regex)) { return false; }
		}
		return true;
	}
}