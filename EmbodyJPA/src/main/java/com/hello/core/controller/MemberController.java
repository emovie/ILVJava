package com.hello.core.controller;


import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hello.core.DTO.MemberDTO;

@Controller
public class MemberController {

	@GetMapping(value = "/login")
	public String login(Model model) {
		return "login";
	}
	
	@GetMapping(value="/login2")
	public String login2(Model model) {
		return "test";
	}
	
	@PostMapping(value = "/login")
	public String adminHome(@Valid MemberDTO member) {
		System.out.println("member : " + member.toString());
		return "admin/myPage";
	}
	
}