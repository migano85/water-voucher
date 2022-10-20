	package com.wv.model;


import java.time.LocalDate;
import java.util.Set;
import org.jooq.Record;
import com.wv.jooq.model.Tables;
import com.wv.jooq.model.tables.pojos.Customers;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/************************************************
* Whether it is POJO or record does not matter, JOOQ only requirement:
* 1- class should have only one constructor matching the select statement, in case it will be used as method reference in record mapping
* 2- any number of constructors in case of a custom method is used in mapping using either lambda or inline implementation of Mapper functional interface
* note: check getAllCustomers() in CustomerRepoJooqImpl.class
**/

@Data
@NoArgsConstructor
@ToString(doNotUseGetters = true)
public class Book {

	private Long bookId;
	private Long numberOfPages;
	private Customer customer;
	private Set<Voucher> vouchersList;
	private LocalDate createdAt;
	private String createBy;
	private LocalDate modifiedAt; //(changed automatically on every record change) will be used for optimistic locking
	private String modifiedBy;
	
	/*
	 * to associate list of books with customer no need to map all fields, it will be like JPA entity in eager mode which will affect performance and load extra useless data, 
	 * if I need more info I can add them later.
	 * in my opinion this method is better than creating Record class just to handle bookId and numberOfPages
	*/
	
	public Book setBook(Record record) {
		
		this.bookId = record.indexOf(Tables.BOOKS.BOOK_ID) != -1 ? record.get(Tables.BOOKS.BOOK_ID): null;
		this.numberOfPages = record.indexOf(Tables.BOOKS.NUMBER_OF_PAGES) != -1 ? record.get(Tables.BOOKS.NUMBER_OF_PAGES): null;
		this.customer = record.indexOf("customers") != -1 ? Customer.setCustomer((Customers)record.get("customers")): null;
				
		return this;
	}
}
