package com.moneymoney.app.transactionsservice.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.moneymoney.app.transactionsservice.entity.Transaction;
import com.moneymoney.app.transactionsservice.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionResource {

	@Autowired
	private TransactionService service;
	
	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/deposit")
	public ResponseEntity<Transaction> deposit(@RequestBody Transaction transaction) {
		ResponseEntity<Double> entity=restTemplate.getForEntity("http://localhost:9090/accounts/" + transaction.getAccountNumber() + "/balance",
				Double.class);
		Double currentBalance = entity.getBody();
		Double updateBalance = service.deposit(transaction.getAccountNumber(),transaction.getTransactionDetails(), currentBalance,transaction.getAmount());
		restTemplate.put("http://localhost:9090/accounts/"+transaction.getAccountNumber()+"?currentBalance="+updateBalance, null);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping("/withdraw")
	public ResponseEntity<Transaction> withdraw(@RequestBody Transaction transaction) {
		ResponseEntity<Double> entity=restTemplate.getForEntity("http://localhost:9090/accounts/" + transaction.getAccountNumber() + "/balance",
				Double.class);
		Double currentBalance = entity.getBody();
		Double updateBalance = service.withdraw(transaction.getAccountNumber(),transaction.getTransactionDetails(), currentBalance,transaction.getAmount());
		restTemplate.put("http://localhost:9090/accounts/"+transaction.getAccountNumber()+"?currentBalance="+updateBalance, null);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/statement")
	public ResponseEntity<CurrentDataSet> getStatement(){
		CurrentDataSet currentDataSet = new CurrentDataSet();
		List<Transaction> transactions = service.getStatement();
		currentDataSet.setTransactions(transactions);
		return new ResponseEntity<CurrentDataSet>(currentDataSet,HttpStatus.OK);
	}
	
	/*
	 * @PostMapping("/transfer") public ResponseEntity<Transaction>
	 * fundTransfer(@RequestBody Transaction senderTransaction,
	 * 
	 * @RequestParam("receiverAccountNumber")int
	 * receiverAccountNumber,@RequestParam("amountToTransfer")double amount) {
	 * Transaction receiverTransaction = new Transaction();
	 * senderTransaction.setAmount(amount);
	 * receiverTransaction.setAccountNumber(receiverAccountNumber);
	 * receiverTransaction.setAmount(amount); ResponseEntity<Double> senderEntity =
	 * restTemplate.getForEntity("http://localhost:9090/accounts/" +
	 * senderTransaction.getAccountNumber() + "/balance", Double.class); Double
	 * currentBalance = senderEntity.getBody(); ResponseEntity<Double>
	 * receiverEntity = restTemplate.getForEntity("http://localhost:9090/accounts/"
	 * + receiverTransaction.getAccountNumber() + "/balance", Double.class); Double
	 * currentBalance2 = receiverEntity.getBody(); Double[] updateBalance =
	 * service.fundtransfer(senderTransaction, receiverTransaction);
	 * restTemplate.put("http://localhost:9090/accounts/" + receiverAccountNumber+
	 * "?currentBalance=" + updateBalance,null); return new
	 * ResponseEntity<>(HttpStatus.CREATED); }
	 */
	 
}
