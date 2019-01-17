package com.capgemini.moneymoney.service;

import java.util.List;

import com.capgemini.moneymoney.app.entity.Account;

public interface AccountService {

	void addNewAccount(Account account);
	
	Account getAccountById(int accountNumber);
	
	public void deleteAccount(int accountNumber);

	List<Account> getAllSavingsAccounts();

	void updateBalance(Account account);
}
