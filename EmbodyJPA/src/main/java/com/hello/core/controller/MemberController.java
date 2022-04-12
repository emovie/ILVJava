package com.hello.core.controller;


import java.util.HashMap;
import java.util.List;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hello.core.DTO.BoardDTO;
import com.hello.core.DTO.JoinMemberDTO;
import com.hello.core.DTO.MemberDTO;
import com.hello.core.repository.BoardRepository;
import com.hello.core.service.BoardService;
import com.hello.core.service.MemberService;
import com.hello.secure.SecureProgram;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final BoardService boardService;
	
	public SecureProgram secureProgram;
	
	@GetMapping(value = "/join")
	public String joinForm(Model model, HttpSession session) {
		model.addAttribute("userName", session.getAttribute("userName"));
		return "member/join";
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
		return "member/login";
	}
	
	@PostMapping(value = "/login")
	public @ResponseBody JSONObject login(@RequestBody HashMap<String, String> param, HttpSession session) {
		HashMap<String, String> map = new HashMap<>();
		JSONObject data;
		
		try {
			MemberDTO member = new MemberDTO(param.get("id"), param.get("pw"));
			String userName = "";
			String path = "";

			System.out.println("login member : "+member.getId() + " " + member.getPw());
			if(secureProgram.xssFilter(member.getId(),member.getPw())) {
				userName = memberService.login(member);
				if(!userName.equals("")) {
					path = "/myPage";
					session.setAttribute("userName", userName);
					session.setMaxInactiveInterval(-1);
				}
				else path = "";
			}
			else path = "";
			map.put("path", path);
		} catch (NoResultException e) {
			map.put("path", "");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("path", "error");
		}
		
		data = new JSONObject(map);
		return data;
	}
	
	@GetMapping(value = "/logout")
	public String logout(Model model, HttpSession session) {
		try {
			session.removeAttribute("userName");
			
			List<BoardDTO> boardList = boardService.findAll();
			model.addAttribute("boardList", boardList);
			model.addAttribute("userName", session.getAttribute("userName"));
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "index";
	}
	
	@GetMapping(value = "/myPage")
	public String myPage(Model model, HttpSession session) {
		if(session.getAttribute("userName") == null) return "login";
		try {
			List<BoardDTO> boardList = boardService.findAll();
			model.addAttribute("boardList" , boardList);
			model.addAttribute("userName", session.getAttribute("userName"));
			return "member/myPage";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
}