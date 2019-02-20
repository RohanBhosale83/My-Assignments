package com.capgemini.cartservice.service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.cartservice.entity.Cart;
import com.capgemini.cartservice.entity.Items;
import com.capgemini.cartservice.exceptions.CartNotFoundException;
import com.capgemini.cartservice.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartRepository cartRepository;
	
	@Override
	public List<Cart> getallcarts() {
		cartRepository.findAll().toString();
		return cartRepository.findAll();
	}

	@Override
	public Cart getcartById(int cartid) {
		Cart cart =	cartRepository.findById(cartid).get();
		if(cart == null) {
			throw new CartNotFoundException();
		}
		return cart;
	}

	@Override
	public Cart addToCart(Cart cart) {
		Cart updateCart = getcartById(cart.getCartId());
		Set<Items> items = updateCart.getItems();			//Items present in cart	
		Set<Items> newItemsToAdd = cart.getItems();			//Items to add into cart		
		Iterator<Items> itr = newItemsToAdd.iterator();
		while(itr.hasNext())
		{
			items.add(itr.next());
		}
		updateCart.setItems(items);
		updateCart.setTotalPrice(cartTotal(updateCart));
		cartRepository.save(updateCart);
		System.out.println("Cart items are: "+cart);
		System.out.println("UpdatedCart items are: "+updateCart);
		return updateCart;
		
	}
	
	@Override
	public Cart removeFromCart(Cart cart) {
		Cart updateCart = getcartById(cart.getCartId());
		Set<Items> items = updateCart.getItems(); 					// Items already in cart
		Set<Items> itemsToRemove = cart.getItems(); 			// Items to remove from cart
		Iterator<Items> itr = itemsToRemove.iterator();
		while (itr.hasNext()) {
			items.remove(itr.next());
		}
		updateCart.setItems(items);
		updateCart.setTotalPrice(cartTotal(updateCart));
		cartRepository.save(updateCart);
		System.out.println("Cart items are: "+cart);
		System.out.println("UpdatedCart items are: "+updateCart);
		return updateCart;
	}

	@Override
	public double cartTotal(Cart cart) {
		double totalPrice = 0.0;
		double cartPrice = 0.0;
		for (Items items : cart.getItems()) {
			System.out.println(items);
			System.out.println("Product total is: " + items.getPrice() * items.getQuantity());
			totalPrice = items.getPrice() * items.getQuantity();
			cartPrice = cartPrice + totalPrice;
		}
		System.out.println("Total Cart Value is: " +cartPrice);
		return cartPrice;
	}

	@Override
	public Cart addCart(Cart cart) {
		return cartRepository.save(cart);
		
	}

	

}
