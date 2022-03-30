package com.hello.core.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Setter;

import lombok.Getter;

@Entity
@Getter @Setter
public class Board {

	@Id @GeneratedValue
	private Long idx;
	private String title;
	private String descript;
	private String link;
	private String link2;
	private Date createDate;
	
}
