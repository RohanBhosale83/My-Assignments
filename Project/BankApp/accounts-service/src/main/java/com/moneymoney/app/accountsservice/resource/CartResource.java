package com.moneymoney.app.accountsservice.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moneymoney.app.accountsservice.entity.Cart;
import com.moneymoney.app.accountsservice.service.CartService;

@RestController
@RequestMapping("/carts")
public class CartResource {

	@Autowired
	private CartService service;
	
	@GetMapping
	public ResponseEntity<List<Cart>> getAllCarts(){
		List<Cart> carts=service.getallcarts();
		return new ResponseEntity<>(carts, HttpStatus.OK);
	}
	
	@GetMapping("/{cartid}")
	public Cart getCart(@PathVariable int cartid){
		Cart cart = service.getcartById(cartid);
		return cart;
	}
	
	@PutMapping
	public Cart updateCart(@RequestBody Cart cart){
		service.updateCart(cart);
		return cart;
	}

}
