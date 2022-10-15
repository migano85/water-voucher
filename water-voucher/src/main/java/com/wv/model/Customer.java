package com.wv.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

import com.wv.jooq.model.tables.Customers;

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
* 1- class should have only one constructor matching the select statement, in case the constructor will be used as method reference in select statement mapping
* 2- any number of constructors in case of a custom method is used in select statement mapping using either lambda or inline implementation of Mapper functional interface
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
	private String createBy;
	private LocalDateTime modifiedAt; //(changed automatically on every record change) will be used for optimistic locking
	private String modifyBy;
	private List<Book> book;
//	public static Function function;
//	private List<Address> addressesList;
//	private List<Order> ordersList;
	
	
	public static Customer setCustomer(com.wv.jooq.model.tables.pojos.Customers cust) {
		if(cust != null) {//set only userImportant fields
			return new Customer(cust.getCustomerId(), cust.getFirstName(), cust.getLastName(), cust.getPhoneNo(), null, null, null, null, null);
		}else {
			return null;
		}
	}
//	public static void setCustomer(Function<Customers, Customer> function) {
//		Customer.function = function;
//	}
}
