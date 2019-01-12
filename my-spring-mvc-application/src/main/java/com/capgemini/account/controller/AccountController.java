package com.capgemini.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.moneymoney.account.SavingsAccount;
import com.capgemini.moneymoney.account.service.SavingsAccountService;
import com.capgemini.moneymoney.exception.AccountNotFoundException;

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
	public String getCreateForm() {
		return "AddNewSavingsAccount";
	}

	@RequestMapping("/createAccount")
	public String createAccount(@RequestParam("accHolderName")String accountHolderName,
			@RequestParam("accountBalance")double accountBalance,
			@RequestParam("rdSalary")boolean salary) {
		account = savingsAccountService.createNewAccount(accountHolderName, accountBalance, salary);
		return "redirect:getAllAccounts";
	}
	
	@RequestMapping("/updateAccount")
	public String update() {
		return "updateaccount";
	}
	
	@RequestMapping("/updateAccountInfo")
	public String getAccount(@RequestParam("accountNumber")int accountNumber, Model model) throws AccountNotFoundException {
		account= savingsAccountService.getAccountById(accountNumber);
		return "AccountDetails";
	}
	
	/*
	 * @RequestMapping("") public String
	 * updateAccount(@RequestParam("accountNumber")int
	 * accountNumber, @RequestParam("")String accountHolderName,
	 * 
	 * @RequestParam("accountType")String type) { return type; }
	 */
	
//	@RequestMapping("/updateAccount")
//	public String updateForm() {
//		return "updateAccount";
//	}
	
	/*
	 * @RequestMapping("/updateAccHN") public String getupdateHolderNameForm() {
	 * return "updateHN"; }
	 * 
	 * @RequestMapping("/updateAccountHolderName") public String
	 * updateHolderName(@RequestParam("accountNumber")int accountNumber,
	 * 
	 * @RequestParam("accHolderName")String accountHolderName) throws
	 * AccountNotFoundException { account
	 * =savingsAccountService.getAccountById(accountNumber);
	 * account.getBankAccount().setAccountHolderName(accountHolderName);
	 * savingsAccountService.updateAccount(account); return
	 * "redirect:getAllAccounts"; }
	 */
	/*
	 * @RequestMapping("/updateSalary") public String getupdateSalariedForm() {
	 * return "updateSalaried"; }
	 * 
	 * @RequestMapping("/updateSalary") public String
	 * updateSalaried(@RequestParam("accountNumber")int accountNumber,
	 * 
	 * @RequestParam("rdSalary")boolean salary) throws AccountNotFoundException {
	 * account =savingsAccountService.getAccountById(accountNumber);
	 * account.setSalary(salary); savingsAccountService.updateAccount(account);
	 * return "rediect:getAllAccounts"; }
	 * 
	 * @RequestMapping("/updateAccHNSal") public String updateNameSalary() { return
	 * "updateAccount"; }
	 * 
	 * @RequestMapping("/updateAccountInfo") public String
	 * updateAccountDetailsForm(@RequestParam("accountNumber")int accountNumber,
	 * 
	 * @RequestParam("accHolderName")String
	 * accountHolderName,@RequestParam("rdSalary")boolean salary) throws
	 * AccountNotFoundException { account
	 * =savingsAccountService.getAccountById(accountNumber);
	 * account.getBankAccount().setAccountHolderName(accountHolderName);
	 * account.setSalary(salary); savingsAccountService.updateAccount(account);
	 * 
	 * return "redirect:getAllAccounts"; }
	 */
	 /* 
	 * @RequestMapping("/updateAccountInfo") public String
	 * updateAccountDetails(@RequestParam("accHolderName")String accountHolderName,
	 * 
	 * @RequestParam("rdSalary") boolean salary) throws AccountNotFoundException {
	 * SavingsAccount sa = null; String accHolderName =
	 * sa.getBankAccount().getAccountHolderName(); boolean salaried = sa.isSalary();
	 * int accNumber= sa.getBankAccount().getAccountNumber();
	 * account=savingsAccountService. updateAccount(sa); return
	 * "redirect:getAllAccounts"; }
	 */
	
	@RequestMapping("/searchAccount")
	public String getSearchAccountForm() {
		return "searchAccount";
	}
	
	@RequestMapping("/searchById")
	public String searchAccountById(@RequestParam("accountNumber")int accountNumber) throws AccountNotFoundException {
		account =savingsAccountService.getAccountById(accountNumber);
		return "redirect:getAllAccounts";
		
	}
	
	@RequestMapping("/currentBalance")
	public String getCurrentBalanceForm() {
		return "currentBalance";
	}
	
	@RequestMapping("/getCurrentBalance")
	public String currentAccountBalance(@RequestParam("accountNumber")int accountNumber) throws AccountNotFoundException {
		account =  savingsAccountService.getAccountById(accountNumber);
		return "redirect:getAllAccounts";
	}
	
	@RequestMapping("/sortByName")
	public String sortAccountsByName() {
		return "sortAccounts";
	}
	
	@RequestMapping("/sortAll")
	public String sortAllAccounts() {
		return "redirect:getAllAccounts";
	}
	@RequestMapping("/closeAccount")
	public String getCloseAccountForm() {
		return "closeAccount";
	}
	
	@RequestMapping("/closeAcc")
	public String closeAccount(@RequestParam("accountNumber")int accountNumber) {
		account = savingsAccountService.deleteAccount(accountNumber);
		return "redirect:getAllAccounts";
	}
	
	@RequestMapping("/deposit")
	public String getDepositForm() {
		return "deposit";
	}
	
	@RequestMapping("/depositAmount")
	public String depositAmount(@RequestParam("accountNumber")int accountNumber,
			@RequestParam("amountToDeposit")double amountToDeposit) throws AccountNotFoundException {
		account=savingsAccountService.getAccountById(accountNumber);
		savingsAccountService.deposit(account, amountToDeposit);
		return "redirect:getAllAccounts";
	}
	
	@RequestMapping("/withdraw")
	public String getWithdrawForm() {
		return "withdraw";
	}
	
	@RequestMapping("/withdrawAmount")
	public String withdrawAmount(@RequestParam("accountNumber")int accountNumber,
			@RequestParam("amountToWithdraw")double amountToWithdraw) throws AccountNotFoundException {
		account=savingsAccountService.getAccountById(accountNumber);
		savingsAccountService.withdraw(account, amountToWithdraw);
		return "redirect:getAllAccounts";
	}
	
	@RequestMapping("/transferAmount")
	public String getFundTransferForm() {
		return "fundTransfer";
	}
	
	@RequestMapping("/transfer")
	public String fundTransfer(@RequestParam("senderAccountNumber")int senderAccountNumber,
			@RequestParam("receiverAccountNumber")int receiverAccountNumber,
			@RequestParam("amountToTransfer")double amountToTransfer) throws AccountNotFoundException {
		SavingsAccount senderSavingsAccount = null;
		SavingsAccount receiverSavingsAccount = null;
		senderSavingsAccount = savingsAccountService.getAccountById(senderAccountNumber);
		receiverSavingsAccount = savingsAccountService.getAccountById(receiverAccountNumber);
		savingsAccountService.fundTransfer(senderSavingsAccount, receiverSavingsAccount, amountToTransfer);
	
		return "redirect:getAllAccounts";
	}
	
	@RequestMapping("/getAllAccounts")
	public String getAllAccounts(Model model) {
		savingsAccounts = savingsAccountService.getAllSavingsAccount();
		model.addAttribute("accounts",savingsAccounts);
		return "AccountDetails";
	}
	
	
}
