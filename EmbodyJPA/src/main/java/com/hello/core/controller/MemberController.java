package com.hello.core.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hello.core.domain.MemberDTO;

@RestController
public class MemberController {

	@GetMapping(value = "/login")
	public String login(Model model) {
		return "login";
	}
	
	@PostMapping(value = "/login")
	public String adminHome(MemberDTO member) {
		
		System.out.println(member.toString());
		
		return "admin/myPage";
	}
	
}