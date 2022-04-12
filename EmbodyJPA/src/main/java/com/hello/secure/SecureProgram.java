package com.hello.secure;

import java.util.regex.Pattern;

public class SecureProgram {

	public boolean xssFilter(String... args) {
		System.out.println("secureProgram : " + args.toString());
		for(String str : args) {
			System.out.println("xssFilter : "+str);
			if(Pattern.matches("<|>|\\(|\"|\\);|\\/|&| ", str)) return false;
		}
		return true;
	}
	
}
