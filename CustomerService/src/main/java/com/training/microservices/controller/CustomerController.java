package com.training.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.training.microservices.bean.Customer;
import com.training.microservices.service.CustomerService;

@RestController
public class CustomerController{
	
	private CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService){
		this.customerService = customerService;
	}
	
	@RequestMapping( path="/register", method = RequestMethod.POST)
	public Customer register(@RequestBody Customer customer){
		return customerService.register(customer);
	}
}