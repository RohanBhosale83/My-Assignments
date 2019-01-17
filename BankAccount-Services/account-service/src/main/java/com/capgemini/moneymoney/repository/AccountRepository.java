package com.capgemini.moneymoney.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.moneymoney.app.entity.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account, Integer> {

	
}
