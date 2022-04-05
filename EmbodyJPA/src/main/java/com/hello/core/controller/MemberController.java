package com.hello.core.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.hello.core.DTO.BoardDTO;
import com.hello.core.DTO.JoinMemberDTO;
import com.hello.core.DTO.MemberDTO;
import com.hello.core.service.BoardService;
import com.hello.core.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final BoardService boardService;
	
	@GetMapping(value = "/join")
	public String joinForm() {
		return "join";
	}
	
	@PostMapping(value = "/join")
	public String join(JoinMemberDTO member, Model model) {
		try {
			memberService.join(member);
			return "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	@GetMapping(value = "/login")
	public String loginForm() {
		return "login";
	}
	
//	@PostMapping(value = "/login")
//	public String login(MemberDTO member, Model model, HttpSession session) {
//		try {
//			String userName = memberService.login(member);
//			List<BoardDTO> boardList = boardService.findAll();
//			model.addAttribute("boardList" , boardList);
//			model.addAttribute("userName", userName);
//			return (!userName.isEmpty() ? "member/myPage" : "redirect:/");
//		} catch (NoResultException e) {
//			model.addAttribute("message","아이디 또는 비밀번호를 확인해주세요.");
//			return "login";
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "error";
//		}
//	}
	
	@PostMapping(value = "/login")
	public String login(HttpServletRequest request, HttpSession session) {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String buffer;
			while((buffer = input.readLine()) != null) {
				if(builder.length() > 0) {
					builder.append("\n");
				}
				builder.append(buffer);
			}
			System.out.println("fetch login : " + builder.toString());
		} catch (NoResultException e) {
			// message 추가하기
			return "login";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "member/myPage";
	}
	
	@GetMapping(value = "/myPage")
	public String myPage(Model model) {
		try {
			List<BoardDTO> boardList = boardService.findAll();
			model.addAttribute("boardList" , boardList);
			return "member/myPage";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
}