package com.capgemini;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.capgemini.moneymoney.app.entity.CurrentAccount;
import com.capgemini.moneymoney.app.entity.SavingsAccount;
import com.capgemini.moneymoney.repository.AccountRepository;

@SpringBootApplication
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	/*
	 * @Bean public RestTemplate template() { return new RestTemplate();
	 * 
	 * }
	 */

	@Bean
	public CommandLineRunner addNewAccount(AccountRepository repository) {
		return (evt) -> {
			repository.save(new SavingsAccount(100, "Rohan Bhosale", 20000.0, true));
			repository.save(new SavingsAccount(101, "Shubham Bhatt", 21000.0, true));
			repository.save(new SavingsAccount(102, "Ankita Ambewadikar", 22000.0, true));
			repository.save(new SavingsAccount(103, "Shubham Raut", 21000.0, true));
			repository.save(new CurrentAccount(105, "Tushar Deore", 20000.0, 40000.0));
		};

	}
}