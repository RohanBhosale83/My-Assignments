package com.capgemini.eshoppingzone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.eshoppingzone.entity.Cart;
import com.capgemini.eshoppingzone.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository repository;
	
	@Override
	public Cart getcartById(int cartid) {
	 Cart cart = repository.findById(cartid).get();
		return cart;
	}
	
	@Override
	public void updateCart(Cart cart) {
		repository.save(cart);
	}

	@Override
	public List<Cart> getallcarts() {
		return repository.findAll();
	}

}
