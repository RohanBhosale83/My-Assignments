package com.capgemini.account.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {

	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping("newSA")
	public String homeCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getServletPath().equals("/newSA")) 
		return "AddNewSavingsAccount";
		return "AddNewSavingsAccount";
	}
}
