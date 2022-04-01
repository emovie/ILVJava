package com.hello.core.domain;

import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
@Builder
public class MemberDTO {

	private String id;

	private String pw;
	
}
