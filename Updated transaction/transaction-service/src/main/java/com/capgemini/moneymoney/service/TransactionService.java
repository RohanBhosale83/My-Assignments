package com.capgemini.moneymoney.service;

public interface TransactionService {

	public Double deposit(int accountNumber,double amount);
	
	public Double withdraw(int accountNumber,double amount);
	
	public Double fundTransfer(int senderAccountNumber,int receiverAccountNumber,double amount);
	
}
