package com.hello.core.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Setter;
import lombok.ToString;
import lombok.Getter;

@Entity
@Getter @Setter
@ToString
public class Board {

	@Id @GeneratedValue
	private Long idx;
	private String title;
	private String descript;
	private String pageLink;
	private String videoLink;
	private String isBoard;
	private String isPage;
	private Long boardLike;
	private String createDate;
	private String isDel;
}
