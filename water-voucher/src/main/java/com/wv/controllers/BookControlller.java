package com.wv.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wv.model.Book;
import com.wv.repositories.BookRepoJooqImpl;
import com.wv.services.BookService;

@RestController
@RequestMapping("/book-api")
public class BookControlller implements IGlobalController<Book>{

	@Autowired
	private BookRepoJooqImpl repoJooqImpl;
	@Autowired
	private BookService service;
	
	@GetMapping("/books")
	public Collection<Book> getAll(){
		return repoJooqImpl.getAll();
	}
	
	@PostMapping("/books")
	public void save(@RequestBody Book book) {
		service.createBookWithVouchers(book);
	}
	
	@GetMapping("books/{id}")
	public Book get(@PathVariable Long id) {
		return repoJooqImpl.get(id).orElse(null);
	}

	@Override
	public void delete(@PathVariable Long id) {
		repoJooqImpl.delete(id);		
	}
}
