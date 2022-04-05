package com.hello.core.DTO;

import lombok.Data;

@Data
public class JoinMemberDTO {

	private final String id;
	private final String pw;
	private final String userName;
	
	public JoinMemberDTO(String id, String pw, String userName) {
		super();
		this.id = id;
		this.pw = pw;
		this.userName = userName;
	}
	
}
