package com.eshoppingzone.app.cartservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.eshoppingzone.app.cartservice.entity.Cart;

@Repository
public interface CartRepository extends MongoRepository<Cart, Integer>{
}
