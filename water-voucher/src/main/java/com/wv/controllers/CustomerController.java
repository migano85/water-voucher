package com.wv.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wv.model.Customer;
import com.wv.repositories.CustomerRepoJdbcImpl;
import com.wv.repositories.CustomerRepoJooqImpl;

@RestController
//@RequestMapping("/custApi")
public class CustomerController implements IGlobalController<Customer>{

	@Autowired
	private CustomerRepoJooqImpl customerRepoJooqImpl;
	@Autowired
	private CustomerRepoJdbcImpl customerRepoJdbcImpl;
	
	@GetMapping("/customers")
	public Collection<Customer> getAll(){
		return customerRepoJooqImpl.getAllWithBooks();
	}
	
	@GetMapping("/customersCount")
	public int getCustomerCount(){
		return customerRepoJdbcImpl.count();
	}
	
	@PostMapping("/customers")
	public void save(@RequestBody Customer customer) {
		customerRepoJooqImpl.save(customer);
	}
	
	@GetMapping("customers/{id}")
	public Customer get(@PathVariable Long id) {
		return customerRepoJooqImpl.get(id).orElse(null);
	}

	@Override
	@DeleteMapping("customers/{id}")
	public void delete(@PathVariable Long id) {
		customerRepoJooqImpl.delete(id);
		
	}
}
