package com.wv.model;

import java.time.LocalDateTime;
import java.util.List;

import org.jooq.Record;

import com.wv.jooq.model.Tables;
import com.wv.jooq.model.tables.pojos.Customers;
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
	private List<Address> addressesList;
	
	
	public Customer setRecord(Record record) {
		
		this.customerId = record.indexOf(Tables.CUSTOMERS.CUSTOMER_ID) != -1 ? record.get(Tables.CUSTOMERS.CUSTOMER_ID): null;
		this.firstName = record.indexOf(Tables.CUSTOMERS.FIRST_NAME) != -1 ? record.get(Tables.CUSTOMERS.FIRST_NAME): null;
				
		return this;
	}
	
	
	public static Customer setCustomer(Customers cust) {
		if(cust != null) {//set only userImportant fields
			return new Customer(cust.getCustomerId(), cust.getFirstName(), cust.getLastName(), cust.getPhoneNo(), null, null, null, null, null, null);
		}else {
			return null;
		}
	}
	
	public static Customer setAll(Long customerId, String firstName, String lastName, Long phoneNo, LocalDateTime createdAt,
		String createBy, LocalDateTime modifiedAt, String modifyBy, List<Book> book) {
	
	return new Customer(customerId, firstName, lastName, phoneNo, createdAt, createBy, modifiedAt, modifyBy, book, null);
	
	}

}
