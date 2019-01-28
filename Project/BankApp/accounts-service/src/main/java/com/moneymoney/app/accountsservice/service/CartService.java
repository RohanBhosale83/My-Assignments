package com.moneymoney.app.accountsservice.service;

import java.util.List;

import com.moneymoney.app.accountsservice.entity.Cart;

public interface CartService {

	public Cart getcartById(int cartid);

	public void updateCart(Cart cart);

	public List<Cart> getallcarts();
}