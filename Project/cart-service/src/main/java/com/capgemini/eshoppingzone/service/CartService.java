package com.capgemini.eshoppingzone.service;

import java.util.List;

import com.capgemini.eshoppingzone.entity.Cart;

public interface CartService {

	public Cart getcartById(int cartid);

	public void updateCart(Cart cart);

	public List<Cart> getallcarts();
}