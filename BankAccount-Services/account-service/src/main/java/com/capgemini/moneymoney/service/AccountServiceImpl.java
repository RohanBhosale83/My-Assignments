package com.capgemini.moneymoney.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.moneymoney.app.entity.Account;
import com.capgemini.moneymoney.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository repository;

	@Override
	public void addNewAccount(Account account) {
		repository.save(account);
	}

	@Override
	public List<Account> getAllSavingsAccounts() {
		return repository.findAll();
	}
	
	@Override
	public Account getAccountById(int accountId) {
		Account account = repository.findById(accountId).get();
		return account;
	}

	@Override
	public void deleteAccount(int accountNumber) {
		repository.deleteById(accountNumber);		
	}

	@Override
	public void updateBalance(Account account) {
		repository.save(account);
		
	}

	
}
