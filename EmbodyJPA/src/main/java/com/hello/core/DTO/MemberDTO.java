package com.hello.core.DTO;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberDTO {
	
	@NotBlank
	private String id;
	@NotBlank
	private String pw;
	
}