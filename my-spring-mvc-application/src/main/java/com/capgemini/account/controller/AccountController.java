package com.capgemini.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.moneymoney.account.SavingsAccount;
import com.capgemini.moneymoney.account.service.SavingsAccountService;

@Controller
public class AccountController {
	@Autowired
	private SavingsAccountService savingsAccountService ;
	
	private List<SavingsAccount> savingsAccounts;
	private SavingsAccount account;
	
	@RequestMapping("/home")
	public String home() {
		return "index";
	}

	@RequestMapping("/newSA")
	public String newSA() {
		return "AddNewSavingsAccount";
	}

	@RequestMapping("/createAccount")
	public String creteAccount(@RequestParam("accHolderName")String accountHolderName,
			@RequestParam("accountBalance")double accountBalance,
			@RequestParam("rdSalary")boolean salary) {
		account = savingsAccountService.createNewAccount(accountHolderName, accountBalance, salary);
		return "redirect:getAllAccounts";
	}
	
	@RequestMapping("/closeAccount")
	public String closeAccount(@RequestParam("accountNumber")int accountNumber) {
		account = savingsAccountService.deleteAccount(accountNumber);
		return "redirect:getAllAccounts";
	}
	
	@RequestMapping("/getAllAccounts")
	public String getAllAccounts(Model model) {
		savingsAccounts = savingsAccountService.getAllSavingsAccount();
		model.addAttribute("accounts",savingsAccounts);
		return "AccountDetails";
	}
	
	
}
