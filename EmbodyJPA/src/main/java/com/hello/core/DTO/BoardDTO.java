package com.hello.core.DTO;

import lombok.Data;

@Data
public class BoardDTO {

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