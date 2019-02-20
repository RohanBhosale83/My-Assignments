package com.capgemini.cartservice.cartresource;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.cartservice.entity.Cart;
import com.capgemini.cartservice.entity.Items;
import com.capgemini.cartservice.service.CartService;

@RestController
@RequestMapping("/carts")
public class CartResource {
	
	@Autowired
	private CartService service;

	/**
	 * 
	 * @returns all present carts
	 */
	@GetMapping
	public ResponseEntity<List<Cart>> getAllCarts() {
		List<Cart> carts = service.getallcarts();
		return new ResponseEntity<>(carts, HttpStatus.OK);
	}

	/**
	 * This method adds Cart into the Database.
	 * @param cart
	 */
	@PostMapping
	public void addCart(@RequestBody Cart cart) {
		service.addCart(cart);
	}
	
	/**
	 * 
	 * @param cartid
	 * @returns cart based on its Id
	 */
	@GetMapping("/{cartid}")
	public ResponseEntity<Cart> getCart(@PathVariable int cartid) {
		Cart cart = service.getcartById(cartid);
		System.out.println(cart);
		if (cart == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(cart, HttpStatus.OK);
	}

	/**
	 * 
	 * @param cart
	 * @returns updated cart - adds product into the cart
	 */
	@PutMapping
	public ResponseEntity<Cart> updateCart(@RequestBody Cart cart) {
		Cart updateCart = service.getcartById(cart.getCartId());
		Set<Items> items = updateCart.getItems();					//Items present in cart	
		Set<Items> newItemsToAdd = cart.getItems();						//Items to add into cart		
		Iterator<Items> itr = newItemsToAdd.iterator();
		while(itr.hasNext())
		{
			items.add(itr.next());
		}
		updateCart.setItems(items);
		updateCart.setTotalPrice(service.cartTotal(updateCart));
		service.updateCart(updateCart);
		return new ResponseEntity<>(updateCart, HttpStatus.OK);
	}

	/**
	 * Delete's cart by it's Id
	 * @param cartid
	 */
	@DeleteMapping
	public void deleteCart(@PathVariable int cartid) {
		service.deleteCart(cartid);
	}

	/**
	 * 
	 * @param cart
	 * @returns cart with present items in it.
	 */
	@PutMapping("/{cartid}")
	public  ResponseEntity<Cart> deleteFromCart(@RequestBody Cart cart) {
		Cart updateCart = service.getcartById(cart.getCartId());
		Set<Items> items = updateCart.getItems();					 // Items already in cart
		Set<Items> itemsToRemove = cart.getItems(); 					// Items to remove from cart
		Iterator<Items> itr = itemsToRemove.iterator();
		while (itr.hasNext()) {
			items.remove(itr.next());
		}
		updateCart.setItems(items);
		updateCart.setTotalPrice(service.cartTotal(updateCart));
		service.updateCart(updateCart);
		return new ResponseEntity<>(updateCart, HttpStatus.OK);
	}
}
