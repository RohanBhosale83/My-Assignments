package com.training.microservices;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.training.microservices.bean.Customer;
import com.training.microservices.repository.CustomerRespository;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner init(CustomerRespository customerRepository) {
		return (evt) -> {
			customerRepository.save(new Customer("Satyen", "satyen@gmail.com"));
			customerRepository.save(new Customer("Vikram", "vikram@gmail.com"));
			customerRepository.save(new Customer("Pooja", "pooja@gmail.com"));
			customerRepository.save(new Customer("Garima", "garima@gmail.com"));
			customerRepository.save(new Customer("Dinesh", "dinesh@gmail.com"));
			customerRepository.save(new Customer("Suresh", "suresh@gmail.com"));
			customerRepository.save(new Customer("Ramesh", "ramesh@gmail.com"));
		};
	}
}
