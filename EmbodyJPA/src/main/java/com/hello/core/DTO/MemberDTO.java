package com.hello.core.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter @Setter @ToString
public class MemberDTO {

	@NotBlank(message = "아이디가 누락되었습니다")
	private String id;
	@NotBlank(message = "비밀번호가 누락되었습니다")
	private String pw;
	
}
