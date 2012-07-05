package com.margus.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class LoginController {
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(){
		
		return "adminLogin";
	}
	
	@RequestMapping(value = "/failLogin", method = RequestMethod.GET)
	public String failLogin(Model model){
		model.addAttribute("error", true);
		return "adminLogin";
	}
	
}
