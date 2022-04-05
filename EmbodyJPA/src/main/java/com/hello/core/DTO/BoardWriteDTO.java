package com.hello.core.DTO;

import lombok.Data;

@Data
public class BoardWriteDTO {

	private String title;
	private String descript;
	private String pageLink;
	private String videoLink;
	private String isBoard;
	private String isPage;
	
}
