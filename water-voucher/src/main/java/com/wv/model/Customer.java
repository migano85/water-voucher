package com.wv.model;

import java.time.LocalDateTime;
import java.util.List;

import org.jooq.Record;

import com.wv.jooq.model.Tables;
import com.wv.jooq.model.tables.pojos.Customers;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
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
@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
@ToString(doNotUseGetters = true, callSuper = true)
public class Customer extends Customers{

	private static final long serialVersionUID = 1L;
	//	private Long customerId;
//	private String firstName;
//	private String lastName;
//	private Long phoneNo;
//	private LocalDateTime createdAt;
//	private String createBy;
//	private LocalDateTime modifiedAt; //(changed automatically on every record change) will be used for optimistic locking
//	private String modifyBy;
	private List<Book> bookList;
	private List<Address> addressesList;
	
	
	public Customer(Customers customers){
		super(customers);
	}
	public Customer(Customers customers, List<Book> bookList, List<Address> addressesList){
		super(customers);
		this.bookList = bookList;
		this.addressesList = addressesList;
	}
	
	public Customer(Long customerId,
	        String firstName,
	        String lastName,
	        Long phoneNo,
	        LocalDateTime createdAt,
	        String createdBy,
	        LocalDateTime modifiedAt,
	        String modifiedBy, List<Book> bookList,
	        List<Address> addressesList) {
		
		super(customerId, firstName, lastName, phoneNo, createdAt, createdBy, modifiedAt, modifiedBy);
		
		this.bookList = bookList;
		this.addressesList = addressesList;
	}

	public Customer setRecord(Record record) {
		
		setCustomerId(record.indexOf(Tables.CUSTOMERS.CUSTOMER_ID) != -1 ? record.get(Tables.CUSTOMERS.CUSTOMER_ID): null);
		setFirstName(record.indexOf(Tables.CUSTOMERS.FIRST_NAME) != -1 ? record.get(Tables.CUSTOMERS.FIRST_NAME): null);
				
		return this;
	}
	
	
	public static Customer setCustomer(Customers cust) {
		if(cust != null) {//set only userImportant fields
			Customer customer = new Customer(cust);
			return customer;
		}else {
			return null;
		}
	}
	
	public static Customer setWithBooks(Long customerId, String firstName, String lastName, Long phoneNo, LocalDateTime createdAt,
		String createdBy, LocalDateTime modifiedAt, String modifieBy, List<Book> bookList) {
	
		Customer customer = new Customer(customerId, firstName, lastName, phoneNo, createdAt, createdBy, modifiedAt, modifieBy, bookList, null);
		return customer;
	}

}
