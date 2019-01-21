package com.moneymoney.app.transactionsservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.moneymoney.app.transactionsservice.entity.Transaction;
import com.moneymoney.app.transactionsservice.repository.TransactionRepository;

@SpringBootApplication
public class TransactionsServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TransactionsServiceApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public CommandLineRunner populateData(TransactionRepository repository) {
		return (arg) -> {
			repository.save(new Transaction(100,5000.0,"Withdrawn","ATM"));
			repository.save(new Transaction(101,5000.0,"Withdrawn","ATM"));
			repository.save(new Transaction(102,5000.0,"Withdrawn","ATM"));
			repository.save(new Transaction(103,5000.0,"Withdrawn","ATM"));
		};
	}
	
	
}

