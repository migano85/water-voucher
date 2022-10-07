package com.wv.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wv.model.Customer;
import com.wv.repositories.CustomerRepoJooqImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
//@RequestMapping("/custApi")
public class CustomerControlller {

	@Autowired
	private CustomerRepoJooqImpl customerRepoJooqImpl;
	
	@GetMapping("/customers")
	public Collection<Customer> getAll(){
		return customerRepoJooqImpl.getAllWithBooks();
	}
	
	@PostMapping("/customers")
	public void save(@RequestBody Customer customer) {
		customerRepoJooqImpl.save(customer);
	}
	
	@GetMapping("employees/{id}")
	public Customer get(@PathVariable Long id) {
		return customerRepoJooqImpl.get(id).orElse(null);
	}
}
