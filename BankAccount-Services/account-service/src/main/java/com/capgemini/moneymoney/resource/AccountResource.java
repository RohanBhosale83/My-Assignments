package com.capgemini.moneymoney.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.moneymoney.app.entity.Account;
import com.capgemini.moneymoney.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountResource {

	@Autowired
	private AccountService service;
	
	@PostMapping	//Uniform Constraint Interface
	public void addNewAccount(@RequestBody Account account) {
		service.addNewAccount(account);
	}

	@GetMapping
	public List<Account> getAllAccounts() {
		return service.getAllSavingsAccounts();
	}
	
	@GetMapping("/{accountNumber}")
	public Account getAccountById(@PathVariable int accountNumber) {
		return service.getAccountById(accountNumber);
	}
	
	@PutMapping("/{accountNumber}")
	public ResponseEntity<Account> updateAccountBalance(@PathVariable int accountNumber,@RequestParam("balance") Double currentBalance) {
		Account account = service.getAccountById(accountNumber);
		if(account==null) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		currentBalance += account.getCurrentBalance();
		account.setCurrentBalance(currentBalance);
		service.updateBalance(account);
		return new ResponseEntity<Account>(account,HttpStatus.OK);
	}
	
	@DeleteMapping("/{accountNumber}")
	public void deleteAccount(@PathVariable int accountNumber) {
		service.deleteAccount(accountNumber);
	}
}
