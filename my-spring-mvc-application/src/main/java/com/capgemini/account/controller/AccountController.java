package com.capgemini.account.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {
	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping("/newSA")
	public String newSA() {
		return "AddNewSavingsAccount";
	}

	@RequestMapping("/createAccount")
	public String redirect(HttpServletRequest request, Model model) {
		String accountHolderName = request.getParameter("accHolderName");
		double accountBalance = Double.parseDouble(request.getParameter("accountBalance"));
		boolean salary = request.getParameter("rdSalary").equals("Yes") ? true : false;

		return "AddNewSavingsAccount";
	}
}
