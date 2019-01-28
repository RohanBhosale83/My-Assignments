package com.capgemini.eshoppingzone.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.eshoppingzone.entity.Cart;

@Repository
public interface CartRepository extends MongoRepository<Cart, Integer>{
}
