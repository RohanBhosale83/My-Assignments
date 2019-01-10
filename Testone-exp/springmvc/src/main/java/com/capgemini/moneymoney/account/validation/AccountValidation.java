package com.capgemini.moneymoney.account.validation;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.capgemini.moneymoney.account.SavingsAccount;

@Component
@Aspect
public class AccountValidation {
	private Logger logger =Logger.getLogger(AccountValidation.class.getName());
	
	@Around("execution(* com.capgemini.moneymoney.account.service.SavingsAccountServiceImpl.deposit(..))")
	public void validateDeposit(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("Function name is:"+pjp.getSignature());
		Object[] params = pjp.getArgs();
		SavingsAccount account = (SavingsAccount) params[0];
		double amountToDeposit = (double) params[1];
		if(amountToDeposit>0) {
		Object actual = pjp.proceed();
		logger.info(" Amount Deposited Successfully !!!");
		}
		else logger.info(" Invalid Input Amount !!!");
	}
	
	@Around("execution(* com.capgemini.moneymoney.account.service.SavingsAccountServiceImpl.withdraw(..))")
	public void validateWithdraw(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("Function name is:"+pjp.getSignature());
		Object[] params = pjp.getArgs();
		SavingsAccount account = (SavingsAccount) params[0];
		double amountToWithdraw = (double) params[1];
		double currentBalance = account.getBankAccount().getAccountBalance();
		if(amountToWithdraw>0 && currentBalance>=amountToWithdraw ) {
		Object actual = pjp.proceed();
		logger.info(" Amount Withdrawn Successfully !!!");
		}
		else logger.info(" Invalid Input or Insufficient Funds! !!!");
	}
	

}
