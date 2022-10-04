package com.wv.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wv.model.Customer;
import com.wv.repositories.CustomerRepoJooqImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CustomerControlller {

	@Autowired
	private CustomerRepoJooqImpl customerRepoJooqImpl;
	
	@GetMapping("/customers")
	public Collection<Customer> getAll(){
		return customerRepoJooqImpl.getAllWithBooks();
	}
	
	
}
