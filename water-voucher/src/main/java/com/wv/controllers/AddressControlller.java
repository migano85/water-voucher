package com.wv.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wv.model.Address;
import com.wv.repositories.AddressRepoJooqImpl;

@RestController
@RequestMapping("/address-api")
public class AddressControlller implements IGlobalController<Address>{

	@Autowired
	private AddressRepoJooqImpl addressRepoJooqImpl;
	
	@GetMapping("/addresses")
	public Collection<Address> getAll(){
		return addressRepoJooqImpl.getAll();
	}
	
	@PostMapping("/addresses")
	public void save(@RequestBody Address book) {
		addressRepoJooqImpl.save(book);
	}
	
	@GetMapping("addresses/{id}")
	public Address get(@PathVariable Long id) {
		return addressRepoJooqImpl.get(id).orElse(null);
	}

	@Override
	public void delete(@PathVariable Long id) {
		addressRepoJooqImpl.delete(id);
	}
}
