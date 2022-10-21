package com.wv.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wv.model.Bottle;
import com.wv.repositories.BottleRepoJooqImpl;

@RestController
public class BottleControlller implements IGlobalController<Bottle>{

	@Autowired
	private BottleRepoJooqImpl bottleRepoJooqImpl;
	
	@GetMapping("/books")
	public Collection<Bottle> getAll(){
		return bottleRepoJooqImpl.getAll();
	}
	
	@PostMapping("/books")
	public void save(@RequestBody Bottle bottle) {
		bottleRepoJooqImpl.save(bottle);
	}
	
	@GetMapping("books/{id}")
	public Bottle get(@PathVariable Long id) {
		return bottleRepoJooqImpl.get(id).orElse(null);
	}

	@Override
	public void delete(@PathVariable Long id) {
		bottleRepoJooqImpl.delete(id);		
	}
}
