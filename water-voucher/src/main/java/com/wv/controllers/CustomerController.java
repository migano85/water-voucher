package com.wv.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wv.model.Customer;
import com.wv.repositories.CustomerRepoJdbcImpl;
import com.wv.repositories.CustomerRepoJooqImpl;

@RestController
@RequestMapping("/customers")
public class CustomerController implements IGlobalController<Customer>{

	@Autowired
	private CustomerRepoJooqImpl repoJooqImpl;
	@Autowired
	private CustomerRepoJdbcImpl repoJdbcImpl;
	
	@GetMapping("/all")
	public Collection<Customer> getAll(){
		return repoJooqImpl.getAllWithBooks();
	}
	
	@GetMapping("/count")
	public int getCustomerCount(){
		return repoJdbcImpl.count();
	}
	
	@PostMapping("/customer")
	public void save(@RequestBody Customer customer) {
		repoJooqImpl.save(customer);
	}
	
	@PostMapping("/search")
	public Collection<Customer> search(@RequestBody Customer customer) {
		return repoJooqImpl.searchCustomerByCriteria(customer);
	}
	
	@GetMapping("/{id}")
	public Customer get(@PathVariable Long id) {
		return repoJooqImpl.get(id).orElse(null);
	}

	@Override
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repoJooqImpl.delete(id);
		
	}
}
