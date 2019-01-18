package com.capgemini.moneymoney.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.moneymoney.app.entity.Transaction;
import com.capgemini.moneymoney.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionRepository repository;
	
	@Autowired
	private Transaction transaction;
	
	@Override
	public Double deposit(int accountNumber, double amount) {
		repository.findById(accountNumber);
		transaction.getAccountNumber();
		return null;
	}

	@Override
	public Double withdraw(int accountNumber, double amount) {
		return null;
	}

	@Override
	public Double fundTransfer(int senderAccountNumber, int receiverAccountNumber, double amount) {
		return null;
	}

}
