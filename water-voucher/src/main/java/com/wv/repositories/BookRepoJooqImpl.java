package com.wv.repositories;

import java.util.Collection;
import java.util.Optional;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.wv.jooq.model.Tables;
import com.wv.model.Book;

public class BookRepoJooqImpl implements IBookRepo{

	@Autowired
    private DSLContext dslContext;
	
	@Override
	public void save(Book t) {
		// TODO Auto-generated method stub
		
	}

	public int count() {
		return dslContext.selectCount().from(Tables.BOOKS).fetchOne().component1();
	}

	@Override
	public Optional<Book> get(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Collection<Book> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
