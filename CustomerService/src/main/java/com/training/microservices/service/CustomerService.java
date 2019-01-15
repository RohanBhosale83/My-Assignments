package com.training.microservices.service;

import com.training.microservices.bean.Customer;

public interface CustomerService {
	public Customer register(Customer customer);
}