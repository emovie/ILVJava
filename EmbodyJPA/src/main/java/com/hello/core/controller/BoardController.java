package com.hello.core.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hello.core.DTO.BoardDTO;
import com.hello.core.DTO.BoardWriteDTO;
import com.hello.core.domain.Board;
import com.hello.core.repository.BoardRepository;
import com.hello.core.service.BoardService;
import com.hello.secure.SecureProgram;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	private final BoardRepository boardRepository;
	
	public SecureProgram secureProgram;
	
	@GetMapping(value = "/")
	public String home(Model model, HttpSession session) {
		try {
			List<BoardDTO> boardList =  boardService.findAllBoard();
			model.addAttribute("boardList", boardList);
			model.addAttribute("userName", session.getAttribute("userName"));
			return "index";
		}catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	@GetMapping(value = "/board/{idx}")
	public String board(@PathVariable("idx") Long idx, Model model, HttpSession session) {
		try {
			BoardDTO board = boardService.boardToBoardDTO(boardRepository.findOne(idx));
			model.addAttribute("board", board);
			model.addAttribute("userName", session.getAttribute("userName"));
			return "board/read";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	@PostMapping(value = "/boardLike")
	public @ResponseBody JSONObject boardLike(@RequestBody HashMap<String, String> param, HttpSession session) {
		Object boardLikeFlag = session.getAttribute("boardLikeFlag");
		HashMap<String, String> map = new HashMap<>();
		String message = "";
		
		if(boardLikeFlag != null) message = "이미 처리되었습니다 !";
		else {
			try {
				map.put("boardLikeCnt", boardService.boardLikeAddOne(param.get("idx")));
				session.setAttribute("boardLikeFlag", "like:-)");
				session.setMaxInactiveInterval(-1);
			} catch (Exception e) {
				e.printStackTrace();
				message = "ERROR";
			}
		}
		map.put("message", message);
		return new JSONObject(map);
	}
	
	// 관리자 접속 Board 
	@GetMapping(value = "/boardWrite")
	public String boardCreateForm(Model model, HttpSession session) {
		if(session.getAttribute("userName") == null) return "login";
		model.addAttribute("userName", session.getAttribute("userName"));
		return "board/myWrite";
	}
	
	@PostMapping(value = "/boardWrite")
	public String boardCreate(BoardWriteDTO board, Model model, HttpSession session) {
		if(session.getAttribute("userName") == null) return "login";
		
		try {
			boardService.boardCreate(board);
			List<BoardDTO> boardList = boardService.findAll();
			model.addAttribute("boardList" , boardList);
			return "member/myPage";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	@GetMapping(value = "/boardRead/{idx}")
	public String boardRead(@PathVariable("idx") Long idx, Model model,HttpSession session) {
		
		if(session.getAttribute("userName") == null) return "login";
		
		try {
			Board findBoard = boardRepository.findOne(idx);
			BoardDTO boardDTO = boardService.boardToBoardDTO(findBoard);
			model.addAttribute("board", boardDTO);
			model.addAttribute("userName", session.getAttribute("userName"));
			return "board/myRead";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	@GetMapping(value = "/boardUpdate/{idx}")
	public String boardUpdateForm(@PathVariable("idx") Long idx, Model model, HttpSession session) {
		
		if(session.getAttribute("userName") == null) return "login";
		
		try {
			Board board = boardRepository.findOne(idx);
			BoardDTO boardDTO = boardService.boardToBoardDTO(board);
			model.addAttribute("board", boardDTO);
			model.addAttribute("userName", session.getAttribute("userName"));
			return "board/myUpdate";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	@PostMapping(value = "/boardUpdate")
	public String boardUpdate(BoardDTO updateBoard, Model model, HttpSession session) {
		System.out.println("updateBoard : "+updateBoard.toString());
		
		if(session.getAttribute("userName") == null) return "login";
		
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
	public String boardDelete(@PathVariable("idx") Long idx, Model model, HttpSession session) {
		
		if(session.getAttribute("userName") == null) return "login";
		
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