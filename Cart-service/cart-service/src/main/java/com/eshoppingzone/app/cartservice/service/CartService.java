package com.eshoppingzone.app.cartservice.service;

import java.util.List;

import com.eshoppingzone.app.cartservice.entity.Cart;

public interface CartService {

	public Cart getcartById(int cartid);

	public void updateCart(Cart cart);

	public List<Cart> getallcarts();
}
