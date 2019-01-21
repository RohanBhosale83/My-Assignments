package com.moneymoney.app.transactionsservice.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moneymoney.app.transactionsservice.entity.Transaction;
import com.moneymoney.app.transactionsservice.entity.TransactionType;
import com.moneymoney.app.transactionsservice.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	private TransactionRepository repository;
	
	@Autowired
	private Transaction transaction;

	@Override
	public Double withdraw(int accountNumber,String transactionDetails,double currentBalance, double amount) {
		transaction.setAccountNumber(accountNumber);
		transaction.setAmount(amount);
		System.out.println(currentBalance);
		currentBalance -= amount;
		transaction.setCurrentBalance(currentBalance);
		System.out.println(currentBalance);
		transaction.setTransactionDate(LocalDateTime.now());
		transaction.setTransactionType(TransactionType.WITHDRAW);
		repository.save(transaction);
		return currentBalance;

	}

	@Override
	public Double deposit(int accountNumber,String transactionDetails,double currentBalance, double amount) {
		transaction.setAccountNumber(accountNumber);
		transaction.setAmount(amount);
		currentBalance += amount;
		transaction.setCurrentBalance(currentBalance);
		transaction.setTransactionDate(LocalDateTime.now());
		transaction.setTransactionType(TransactionType.DEPOSIT);
		repository.save(transaction);
		return currentBalance;
	}

	@Override
	public Double[] fundTransfer(int senderAccountNumber,String transactionDetails,double currentBalance,int recieverAccountNumber, double amount) {
	Double senderBalance = withdraw(senderAccountNumber, transactionDetails, currentBalance, amount);
	Double recieverBalance = deposit(recieverAccountNumber, transactionDetails, currentBalance, amount);
		return new Double[] {senderBalance,recieverBalance};

	}
	

	@Override
	public Double[] fundtransfer(Transaction senderTransaction, Transaction receiverTransaction) {
		Double senderBalance= withdraw(senderTransaction.getAccountNumber(),senderTransaction.getTransactionDetails(),senderTransaction.getCurrentBalance(),senderTransaction.getAmount());
		Double receiverBalance = deposit(receiverTransaction.getAccountNumber(),receiverTransaction.getTransactionDetails(),receiverTransaction.getCurrentBalance(),receiverTransaction.getAmount());
		return new Double[] {senderBalance,receiverBalance};
	}
	
	@Override
	public List<Transaction> getStatement(LocalDate startDate,LocalDate endDate){
		return repository.findAll();
	}

	@Override
	public List<Transaction> getStatement() {
		return repository.findAll();
	}

}
