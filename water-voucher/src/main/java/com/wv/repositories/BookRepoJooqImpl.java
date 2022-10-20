package com.wv.repositories;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wv.jooq.model.Tables;
import com.wv.model.Book;
import com.wv.model.Voucher;

@Repository
public class BookRepoJooqImpl implements IBookRepo{

	@Autowired
    private DSLContext dslContext;
	
	@Override
	public void save(Book book) {
		Long customerId = book.getCustomer() != null?book.getCustomer().getCustomerId() : null;
		Long bookId = 
			dslContext.insertInto(Tables.BOOKS, 
				Tables.BOOKS.NUMBER_OF_PAGES, Tables.BOOKS.CUSTOMER_ID, Tables.BOOKS.CREATED_AT, Tables.BOOKS.CREATED_BY, Tables.BOOKS.MODIFIED_AT, Tables.BOOKS.MODIFIED_BY)
			.values(book.getNumberOfPages(), customerId, book.getCreatedAt(), book.getCreateBy(), book.getModifiedAt(), book.getModifiedBy())
//			.values(getAll());//to insert multiple records
			.returningResult(Tables.BOOKS.BOOK_ID)
			.fetchOne()
			.component1();
		
		book.setBookId(bookId);
	}

	public int count() {
		return dslContext.selectCount().from(Tables.BOOKS).fetchOne().component1();
	}

	@Override
	public Optional<Book> get(Long id) {
		Book book = 
				dslContext.select(Tables.BOOKS)
				.from(Tables.BOOKS)
				.where(Tables.BOOKS.BOOK_ID.eq(id))
				.fetchOneInto(Book.class);
		
		return Optional.of(book);
	}

	@Override
	public Collection<Book> getAll() {
		
		Collection<Book> bookList = 
				dslContext.select(
						Tables.BOOKS.BOOK_ID
						,Tables.BOOKS.NUMBER_OF_PAGES
						,Tables.BOOKS.CREATED_AT
						,Tables.BOOKS.CREATED_BY
						,Tables.BOOKS.MODIFIED_AT
						,Tables.BOOKS.MODIFIED_BY
						,Tables.BOOKS.customers().mapping(com.wv.jooq.model.tables.pojos.Customers::new).as("customers")
				)
				.from(Tables.BOOKS)
				.fetch(r ->r.map(rec->new Book().setBook(rec)));
		
		return bookList;
	}

	@Override
	public void delete(Long id) {
		dslContext.delete(Tables.BOOKS)
			.where(Tables.BOOKS.BOOK_ID.eq(id))
			.execute();
	}
	
	@Override
	public void fillBookVouchers(Long bookId, List<Voucher> vouchersList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteVoucher(Long bookId, Long voucherId) {
		// TODO Auto-generated method stub
		
	}
}
