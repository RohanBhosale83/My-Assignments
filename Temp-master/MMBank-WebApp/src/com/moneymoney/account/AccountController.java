package com.moneymoney.account;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moneymoney.account.service.SavingsAccountService;
import com.moneymoney.account.service.SavingsAccountServiceImpl;
import com.moneymoney.account.util.DBUtil;
import com.moneymoney.exception.AccountNotFoundException;

/**
 * Servlet implementation class AccountController
 */
@WebServlet("*.mm")
public class AccountController extends HttpServlet {
	private RequestDispatcher dispatcher;
	private SavingsAccountService savingsAccountService=new SavingsAccountServiceImpl();
	private SavingsAccount savingsAccount = null;
	private static final long serialVersionUID = 1L;
	private boolean sort = false;
	ArrayList<SavingsAccount> accountslist;
	private SavingsAccount account;

	@Override
	public void init(){
		
	}
    /**
     * Default constructor. 
     */
    public AccountController() {
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
		if (request.getServletPath().equals("/newSA.mm")) {
			response.sendRedirect("AddNewSavingsAccount.jsp");

		}

		else if (request.getServletPath().equals("/create.mm")) {
			String name = request.getParameter("accHolderName");
			double balance = Double.parseDouble(request.getParameter("accountBalance"));
			System.out.println(request.getParameter("rdSalary"));
			try {
				savingsAccountService.createNewAccount(name, balance, true);
				Collection<SavingsAccount> accounts = savingsAccountService.getAllSavingsAccount();
				request.setAttribute("accounts", accounts);
				dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
				dispatcher.forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (request.getServletPath().equals("/updateAccountDetails.mm")){
			response.sendRedirect("updateAccount.jsp");
		}
		else if(request.getServletPath().equals("/updateAccount.mm")){
			int accountNumber= Integer.parseInt(request.getParameter("accountNumber"));
			try {
				try {
					savingsAccount = savingsAccountService.getAccountById(accountNumber);
					request.setAttribute("account", savingsAccount);
					dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
					dispatcher.forward(request, response);
					DBUtil.commit();
				} catch (AccountNotFoundException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		
		else if(request.getServletPath().equals("/updateInDB.mm")) {
			
			System.out.println(account.getBankAccount().getAccountNumber());
			String accountHolderName = request.getParameter("name");
			boolean salary = request.getParameter("salary").equalsIgnoreCase(
					"salaryTrue") ? true : false;
			System.out.println(salary);
			account.getBankAccount().setAccountHolderName(accountHolderName);
			account.setSalary(salary);
			
			try {
				account = savingsAccountService.updateAccount(account);
			} catch (ClassNotFoundException | SQLException | AccountNotFoundException e) {
				e.printStackTrace();
			}
			request.setAttribute("account", account);
			dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
			dispatcher.forward(request, response);

		}
		
		if (request.getServletPath().equals("/closeAccount.mm")){
			response.sendRedirect("closeAccount.jsp");
		}
		else if(request.getServletPath().equals("/closeacc.mm")){
			int accountNumber= Integer.parseInt(request.getParameter("accountNumber"));
			try {
				savingsAccountService.deleteAccount(accountNumber);
				Collection<SavingsAccount> accounts = savingsAccountService.getAllSavingsAccount();
				request.setAttribute("accounts", accounts);
				dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
				dispatcher.forward(request, response);
				//DBUtil.commit();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (request.getServletPath().equals("/deposit.mm")){
			response.sendRedirect("deposit.jsp");
		}
		else if(request.getServletPath().equals("/depositAmount.mm")){
			int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
			double amount= Double.parseDouble(request.getParameter("amountToDeposit"));
			try {
				savingsAccount = savingsAccountService.getAccountById(accountNumber);
				
			} catch (ClassNotFoundException | SQLException
					| AccountNotFoundException e1) {
				e1.printStackTrace();
			}
			try {
				savingsAccountService.deposit(savingsAccount, amount);
				try {
					savingsAccount = savingsAccountService.getAccountById(accountNumber);
					request.setAttribute("account", savingsAccount);
					dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
					dispatcher.forward(request, response);
					DBUtil.commit();
				} catch (AccountNotFoundException e) {
					e.printStackTrace();
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

		if (request.getServletPath().equals("/withdraw.mm")){
			response.sendRedirect("withdraw.jsp");
		}
		else if(request.getServletPath().equals("/withdrawAmount.mm")){
	
			int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
			double amount= Double.parseDouble(request.getParameter("amountToWithdraw"));
			try {
				savingsAccount = savingsAccountService.getAccountById(accountNumber);
			} catch (ClassNotFoundException | SQLException
					| AccountNotFoundException e1) {
				e1.printStackTrace();
			}
			try {
				savingsAccountService.withdraw(savingsAccount, amount);
				try {
					savingsAccount = savingsAccountService.getAccountById(accountNumber);
					request.setAttribute("account", savingsAccount);
					dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
					dispatcher.forward(request, response);
					DBUtil.commit();
				} catch (AccountNotFoundException e) {
					e.printStackTrace();
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (request.getServletPath().equals("/transferAmount.mm")){
			response.sendRedirect("fundTransfer.jsp");
		}
		else if(request.getServletPath().equals("/transfer.mm")){
			int sendersAccountNumber = Integer.parseInt(request.getParameter("senderAccountNumber"));
			int receiversAccountNumber = Integer.parseInt(request.getParameter("receiverAccountNumber"));
			double amount= Double.parseDouble(request.getParameter("amountToTransfer"));
			SavingsAccount senderSavingsAccount = null;
			SavingsAccount receiverSavingsAccount = null;
			try {
				senderSavingsAccount = savingsAccountService.getAccountById(sendersAccountNumber);
				receiverSavingsAccount = savingsAccountService.getAccountById(receiversAccountNumber);
			} catch (ClassNotFoundException | SQLException
					| AccountNotFoundException e1) {
				e1.printStackTrace();
			}
			try {
				savingsAccountService.fundTransfer(senderSavingsAccount, receiverSavingsAccount, amount);
				try {
					savingsAccount = savingsAccountService.getAccountById(senderSavingsAccount.getBankAccount().getAccountNumber());
					DBUtil.commit();
					savingsAccount = savingsAccountService.getAccountById(receiverSavingsAccount.getBankAccount().getAccountNumber());
					request.setAttribute("account", savingsAccount);
					dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
					dispatcher.forward(request, response);
					DBUtil.commit();
				} catch (AccountNotFoundException e) {
					e.printStackTrace();
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (request.getServletPath().equals("/searchAccount.mm")){
			response.sendRedirect("searchAccount.jsp");
		}
		else if(request.getServletPath().equals("/searchById.mm")){
			int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
		
			try {
				savingsAccount = savingsAccountService.getAccountById(accountNumber);
				request.setAttribute("account", savingsAccount);
				dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
				dispatcher.forward(request, response);
				DBUtil.commit();
			} catch (ClassNotFoundException | SQLException
					| AccountNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		
		if (request.getServletPath().equals("/currentBalance.mm")){
			response.sendRedirect("currentBalance.jsp");
		}
		else if(request.getServletPath().equals("/getCurrentBalance.mm")){
			int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
		
			try {
				savingsAccount = savingsAccountService.getAccountById(accountNumber);
				request.setAttribute("account", savingsAccount);
				dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
				dispatcher.forward(request, response);
				DBUtil.commit();
			} catch (ClassNotFoundException | SQLException
					| AccountNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		
		if (request.getServletPath().equals("/getAllSavingAccounts.mm")){
			try {
				Collection<SavingsAccount> accounts = savingsAccountService.getAllSavingsAccount();
				request.setAttribute("accounts", accounts);
				dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
				dispatcher.forward(request, response);
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		if(request.getServletPath().equals("/sortByAccountNumber.mm")){
			sort = !sort;
			int result = sort ? 1 : -1;
			try {
			    accountslist = (ArrayList<SavingsAccount>) savingsAccountService.getAllSavingsAccount();
				Collections.sort(accountslist,new Comparator<SavingsAccount>() {
							@Override
							public int compare(SavingsAccount accountOne,SavingsAccount accountTwo) {
								if (accountOne.getBankAccount().getAccountNumber() < accountTwo.getBankAccount().getAccountNumber())
									return -1 * result;
								else if (accountOne.getBankAccount().getAccountNumber() == accountTwo.getBankAccount().getAccountNumber())
									return 0;
								else
									return 1 * result;
							}

						});
				request.setAttribute("accounts", accountslist);
				dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
				dispatcher.forward(request, response);

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(request.getServletPath().equals("/sortByName.mm")){
			sort = !sort;
			int result = sort ? 1 : -1;
			try {
				accountslist = (ArrayList<SavingsAccount>) savingsAccountService.getAllSavingsAccount();
				Set<SavingsAccount> accountSet = new TreeSet<>(new Comparator<SavingsAccount>() {
					@Override
					public int compare(SavingsAccount accountOne,
							SavingsAccount accountTwo) {
						return result * accountOne.getBankAccount().getAccountHolderName().
								compareToIgnoreCase(accountTwo.getBankAccount().getAccountHolderName());
					}

				});
				
		System.out.println(sort);
		request.setAttribute("accounts", accountslist);
		dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
		dispatcher.forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(request.getServletPath().equals("/sortByBalance.mm")){
			sort = !sort;
			int result = sort ? 1 : -1;
			try {
				accountslist = (ArrayList<SavingsAccount>) savingsAccountService.getAllSavingsAccount();
				Collections.sort(accountslist,new Comparator<SavingsAccount>() {
							@Override
							public int compare(SavingsAccount accountOne,SavingsAccount accountTwo) {
								if (accountOne.getBankAccount().getAccountBalance() < accountTwo.getBankAccount().getAccountBalance())
									return -1 * result;
								else if (accountOne.getBankAccount().getAccountBalance() == accountTwo.getBankAccount().getAccountBalance())
									return 0;
								else
									return 1 * result;
							}

						});
				
				request.setAttribute("accounts", accountslist);
				dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
				dispatcher.forward(request, response);

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
			
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}