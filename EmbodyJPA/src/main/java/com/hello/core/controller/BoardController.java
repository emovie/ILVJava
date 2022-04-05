package com.hello.core.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hello.core.DTO.BoardDTO;
import com.hello.core.DTO.BoardWriteDTO;
import com.hello.core.domain.Board;
import com.hello.core.repository.BoardRepository;
import com.hello.core.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	private final BoardRepository boardRepository;
	
	@GetMapping(value = "/boardWrite")
	public String boardCreateForm() {
		return "board/write";
	}
	
	@PostMapping(value = "/boardWrite")
	public String boardCreate(BoardWriteDTO board, Model model) {
		try {
			boardService.boardCreate(board);
			List<BoardDTO> boardList = boardService.findAll();
			model.addAttribute("boardList" , boardList);
			return "member/myPage";
//			model.addAttribute("boardIdx",boardIdx);
//			return "board/read?boardIdx=" + boardIdx;
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	@GetMapping(value = "/boardRead/{idx}")
	public String boardRead(@PathVariable("idx") Long idx, Model model) {
		try {
			Board findBoard = boardRepository.findOne(idx);
			BoardDTO boardDTO = boardService.boardToBoardDTO(findBoard);
			model.addAttribute("board", boardDTO);
			return "board/read";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	@GetMapping(value = "/boardUpdate/{idx}")
	public String boardUpdateForm(@PathVariable("idx") Long idx, Model model) {
		try {
			Board board = boardRepository.findOne(idx);
			BoardDTO boardDTO = boardService.boardToBoardDTO(board);
			model.addAttribute("board", boardDTO);
			return "board/update";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	@PostMapping(value = "/boardUpdate")
	public String boardUpdate(BoardDTO updateBoard, Model model) {
		try {
			boardService.boardUpdate(updateBoard);
			List<BoardDTO> boardList = boardService.findAll();
			model.addAttribute("boardList" , boardList);
			return "member/myPage";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	@GetMapping(value = "/boardDelete/{idx}")
	public String boardDelete(@PathVariable("idx") Long idx, Model model) {
		try {
			boardRepository.boardDelete(idx);
			List<BoardDTO> boardList = boardService.findAll();
			model.addAttribute("boardList" , boardList);
			return "member/myPage";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
}