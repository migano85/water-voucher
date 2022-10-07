	package com.wv.model;


import java.util.Date;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//public record Book(
//
//	 Long bookId,
//	 Long numberOfPages
////	Customer customer,
////	 Set<Voucher> vouchersList,
////	 Date createdAt,
////	 Long createBy,
////	 Date modifiedAt, //(changed automatically on every record change) will be used for optimistic locking
////	 Long modifyBy
//) {}

/************************************************
* Whether it is POJO or record does not matter, JOOQ only requirement:
* 1- class should have only one constructor matching the select statement, in case it will be used as method reference in record mapping
* 2- any number of constructors in case of a custom method is used in mapping using either lambda or inline implementation of Mapper functional interface
* note: check getAllCustomers() in CustomerRepoJooqImpl.class
**/

@Data
//@AllArgsConstructor
@NoArgsConstructor
@ToString(doNotUseGetters = true)
public class Book {

	private Integer bookId;
	private Long numberOfPages;
	private Customer customer;
	private Set<Voucher> vouchersList;
	private Date createdAt;
	private Long createBy;
	private Date modifiedAt; //(changed automatically on every record change) will be used for optimistic locking
	private Long modifyBy;
	
	/*
	 * to associate list of books with customer non need to map all fields, it will be like JPA entity in eager mode which will affect performance and load extra useless data, 
	 * if I need more info I can add them later.
	 * in my opinion this method is better than creating Record class just to handle bookId and numberOfPages
	*/
	public Book setBookOfCustomer(Integer bookId, Long numberOfPages) {
		this.bookId = bookId;
		this.numberOfPages = numberOfPages;
		return this;
	}
}
