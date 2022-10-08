package com.wv.model;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;


//public record Customer(
//		 Long customerId,
//		 String firstName,
//		 String lastName,
//		 Long phoneNo,
////		 List<Address> addressesList,
//		 LocalDateTime createdAt,
//		 LocalDateTime modifiedAt,
//		 String createUserId,
//		 String modifyUserId,
//		 List<Book> books
//		 ){
//	
//}

/************************************************
* Whether it is POJO or record does not matter, JOOQ only requirement:
* 1- class should have only one constructor matching the select statement, in case it will be used as method reference in record mapping
* 2- any number of constructors in case of a custom method is used in mapping using either lambda or inline implementation of Mapper functional interface
* note: check getAllCustomers() in CustomerRepoJooqImpl.class
**/
@Data
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class Customer {

	private Long customerId;
	private String firstName;
	private String lastName;
	private Long phoneNo;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt; //(changed automatically on every record change) will be used for optimistic locking
	private String createUserId;
	private String modifyUserId;
	private List<Book> book;
//	private List<Address> addressesList;
//	private List<Order> ordersList;
}
