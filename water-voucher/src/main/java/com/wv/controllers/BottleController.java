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

import com.wv.model.Bottle;
import com.wv.repositories.BottleRepoJooqImpl;

@RestController
@RequestMapping("/bottles")
public class BottleController implements IGlobalController<Bottle> {

	@Autowired
	private BottleRepoJooqImpl repoJooqImpl;

	@Override
	@GetMapping("/all")
	public Collection<Bottle> getAll() {
		return repoJooqImpl.getAll();
	}

	@Override
	@PostMapping("/bottle")
	public void save(@RequestBody Bottle bottle) {
		repoJooqImpl.save(bottle);
	}

	@PostMapping("/search")
	public Collection<Bottle> search(@RequestBody Bottle bottle) {
		return repoJooqImpl.searchBottlesByCriteria(bottle);
	}

	@Override
	@GetMapping("/{id}")
	public Bottle get(@PathVariable Long id) {
		return repoJooqImpl.get(id).orElse(null);
	}

	@Override
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repoJooqImpl.delete(id);
	}
}
