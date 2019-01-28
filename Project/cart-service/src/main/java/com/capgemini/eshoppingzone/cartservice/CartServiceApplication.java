package com.capgemini.eshoppingzone.cartservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.capgemini.eshoppingzone.entity.Cart;
import com.capgemini.eshoppingzone.entity.Product;
import com.capgemini.eshoppingzone.repository.CartRepository;

@SpringBootApplication
public class CartServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner populateData(CartRepository repository) {
		return (arg) -> {
			repository.save(new Cart(1,100,new Product(100, "Nokia", "Electronics")));
			repository.save(new Cart(1,101,new Product(101, "Samsung", "Electronics")));
			repository.save(new Cart(1,102,new Product(102, "iphone", "Electronics")));
			repository.save(new Cart(1,103,new Product(103, "Redmi", "Electronics")));
			repository.save(new Cart(1,104,new Product(104, "Sony", "Electronics")));
		};
	
	}
}