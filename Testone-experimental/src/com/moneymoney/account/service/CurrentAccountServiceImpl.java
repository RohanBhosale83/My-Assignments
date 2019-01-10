package com.moneymoney.account.service;

import java.sql.SQLException;
import java.util.List;

import com.moneymoney.account.CurrentAccount;
import com.moneymoney.account.dao.CurrentAccountDAO;
import com.moneymoney.account.dao.CurrentAccountDAOImpl;
import com.moneymoney.account.factory.AccountFactory;
import com.moneymoney.account.util.DBUtil;
import com.moneymoney.exception.AccountNotFoundException;
import com.moneymoney.exception.InsufficientFundsException;
import com.moneymoney.exception.InvalidInputException;

public class CurrentAccountServiceImpl implements CurrentAccountService {

	private AccountFactory factory;
	private CurrentAccountDAO CurrentAccountDAO;

	public CurrentAccountServiceImpl() {
		factory = AccountFactory.getInstance();
		CurrentAccountDAO = new CurrentAccountDAOImpl();
	}

	@Override
	public CurrentAccount createNewAccount(String accountHolderName, double accountBalance, double odLimit)
			throws ClassNotFoundException, SQLException {
		CurrentAccount account = factory.createNewCurrentAccount(accountHolderName, accountBalance, odLimit);
		CurrentAccountDAO.createNewAccount(account);
		return null;
	}

	@Override
	public List<CurrentAccount> getAllCurrentAccount() throws ClassNotFoundException, SQLException {
		return CurrentAccountDAO.getAllCurrentAccount();
	}

	@Override
	public void deposit(CurrentAccount account, double amount) throws ClassNotFoundException, SQLException {
		if (amount > 0) {
			double currentBalance = account.getBankAccount().getAccountBalance();
			currentBalance += amount;
			CurrentAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
			//CurrentAccountDAO.commit();
		}else {
			throw new InvalidInputException("Invalid Input Amount!");
		}
	}
	
	@Override
	public void withdraw(CurrentAccount account, double amount) throws ClassNotFoundException, SQLException {
		double currentBalance = account.getBankAccount().getAccountBalance();
		if (amount > 0 && currentBalance >= amount) {
			currentBalance -= amount;
			CurrentAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
			//CurrentAccountDAO.commit();
		} else {
			throw new InsufficientFundsException("Invalid Input or Insufficient Funds!");
		}
	}

	@Override
	public void fundTransfer(CurrentAccount sender, CurrentAccount receiver, double amount)
			throws ClassNotFoundException, SQLException {
		try {
			withdraw(sender, amount);
			deposit(receiver, amount);
			DBUtil.commit();
		} catch (InvalidInputException | InsufficientFundsException e) {
			e.printStackTrace();
			DBUtil.rollback();
		} catch(Exception e) {
			e.printStackTrace();
			DBUtil.rollback();
		}
	}

	@Override
	public CurrentAccount updateAccount(int accountNumber,int userChoice,String nameORSalary) throws ClassNotFoundException, SQLException {
		return CurrentAccountDAO.updateAccount(accountNumber, userChoice,nameORSalary);
	}

	@Override
	public CurrentAccount getAccountByName(String accountHolderName)throws ClassNotFoundException, SQLException,AccountNotFoundException {
		
		return CurrentAccountDAO.getAccountByName(accountHolderName);
	}
	
	@Override
	public CurrentAccount getAccountById(int accountNumber)throws ClassNotFoundException, SQLException,AccountNotFoundException {
		
		return CurrentAccountDAO.getAccountById(accountNumber);
	}

	@Override
	public List<CurrentAccount> getAccountsByBalance(double minimumBalance, double maximumBalance)throws ClassNotFoundException, SQLException,AccountNotFoundException {
		return CurrentAccountDAO.getAccountsByBalance(minimumBalance, maximumBalance);
	}
	
	@Override
	public CurrentAccount deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException {
		
		return CurrentAccountDAO.deleteAccount(accountNumber);
	}

	@Override
	public CurrentAccount showCurrentBalance(int accountNumber)throws ClassNotFoundException, SQLException,AccountNotFoundException {
		
		return CurrentAccountDAO.showCurrentBalance(accountNumber);
	}

	@Override
	public List<CurrentAccount> sortAllCurrentAccount(int choice)throws ClassNotFoundException, SQLException {
	
		return CurrentAccountDAO.sortAllCurrentAccount(choice);
	}
	

}