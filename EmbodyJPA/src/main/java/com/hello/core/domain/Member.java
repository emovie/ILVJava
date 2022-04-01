package com.hello.core.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Builder
public class Member {
	
	@Id
	private String id;
	private String pw;
	private String username;
	
}