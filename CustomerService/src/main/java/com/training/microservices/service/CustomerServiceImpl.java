package com.training.microservices.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.microservices.bean.Customer;
import com.training.microservices.messaging.Sender;
import com.training.microservices.repository.CustomerRespository;

@Service
public class CustomerServiceImpl implements CustomerService {
	private CustomerRespository customerRespository;
	private Sender sender;

	@Autowired
	public CustomerServiceImpl(CustomerRespository customerRespository, Sender sender) {
		this.customerRespository = customerRespository;
		this.sender = sender;
	}

	public Customer register(Customer customer) {
		Optional<Customer> existingCustomer = customerRespository.findByName(customer.getName());
		if (existingCustomer.isPresent()) {
			throw new RuntimeException("is already exists");
		} else {
			customerRespository.save(customer);
			sender.send(customer.getEmail());
		}
		return customer;
	}
}
