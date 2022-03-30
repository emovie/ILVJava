package com.hello.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

	@GetMapping(value = "/login")
	public String login(Model model) {
		return "admin/login";
	}
	
	@PostMapping(value = "/login")
	public String adminHome(Model model) {
		return "admin/home";
	}
	
}
