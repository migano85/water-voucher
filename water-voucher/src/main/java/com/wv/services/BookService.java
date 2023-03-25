package com.wv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wv.model.Book;
import com.wv.repositories.BookRepoJooqImpl;

@Service
public class BookService {

    @Autowired
	private BookRepoJooqImpl repoJooqImpl;
    
    public void createBookWithVouchers(Book book){
        repoJooqImpl.save(book);
        //loop on number of pages using java 8 code and insert voucher records accordinglly
        //or use jooq to insert list of vouchers at same time
        book.getNumberOfPages();
    }
}
