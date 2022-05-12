package com.hello.core.restController;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hello.core.DTO.BoardDTO;
import com.hello.core.DTO.MemberDTO;
import com.hello.core.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardRestController {

	private final BoardService boardService;
	
	@GetMapping(value = "/hello")
	public List<String> hello() {
		return Arrays.asList("hello","안녕하세요");
	}
	
	@GetMapping(value="/home")
	public List<BoardDTO> home() {
		List<BoardDTO> result = null;
		
		try {
			result = boardService.findAllBoard();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
}