package com.wv.repositories;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

//import org.jooq.DSLContext;
//import org.jooq.Records;
import org.jooq.*;
import org.jooq.impl.*;
import static org.jooq.impl.DSL.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wv.jooq.model.Tables;
import com.wv.jooq.model.tables.pojos.Books;
import com.wv.jooq.model.tables.pojos.Customers;
import com.wv.model.Book;
import com.wv.model.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class CustomerRepoJooqImpl implements CustomerRepo{

	@Autowired
    private DSLContext dslContext;
	
//	@Override
//	public int save(Customer customer) {
//		log.info("start saving customers using jooq");
//		return dslContext.insertInto(Tables.CUSTOMERS, 
//			Tables.CUSTOMERS.CUSTOMER_ID, Tables.CUSTOMERS.FIRST_NAME, Tables.CUSTOMERS.LAST_NAME, Tables.CUSTOMERS.PHONE_NO, Tables.CUSTOMERS.CREATED_AT, Tables.CUSTOMERS.CREATE_USER_ID, Tables.CUSTOMERS.MODIFIED_AT, Tables.CUSTOMERS.MODIFY_USER_ID)
//			.values(customer.getCustomerId(), customer.getFirstName(), customer.getLastName(), customer.getPhoneNo(),customer.getCreatedAt(), customer.getCreateUserId(), customer.getModifiedAt(), customer.getModifyUserId())
//			.execute() ;
//	}
	
	@Override
	public int save(Customer customer) {
		log.info("start saving customers using jooq");
		return 0;
//		return dslContext.insertInto(Tables.CUSTOMERS, 
//			Tables.CUSTOMERS.CUSTOMER_ID, Tables.CUSTOMERS.FIRST_NAME, Tables.CUSTOMERS.LAST_NAME, Tables.CUSTOMERS.PHONE_NO, Tables.CUSTOMERS.CREATED_AT, Tables.CUSTOMERS.CREATE_USER_ID, Tables.CUSTOMERS.MODIFIED_AT, Tables.CUSTOMERS.MODIFY_USER_ID)
//			.values(customer.customerId(), customer.firstName(), customer.lastName(), customer.phoneNo(),customer.createdAt(), customer.createUserId(), customer.modifiedAt(), customer.modifyUserId())
//			.execute() ;
	}

	@Override
	public int count() {
		log.info("start counting customers using jooq");
		return dslContext.fetchCount(Tables.CUSTOMERS);
	}

	@Override
	public Optional<Customer> get(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Collection<Customer> getAll() {
		 return dslContext.selectFrom(Tables.CUSTOMERS).fetchInto(Customer.class);
		
		 //return null;
	}
	
	public Collection<Customer> getAllCustomers() {
		
		//Result<Record1<String>> d = 
				/*dslContext.select(
						groupConcat(Tables.BOOKS.NUMBER_OF_PAGES).as("bibi"))
						.from(Tables.BOOKS)
						.where(Tables.BOOKS.CUSTOMER_ID.eq(Tables.BOOKS.CUSTOMER_ID)).fetch();
						*/
		
		//*******
		// var keyword was introduced in java 10
		// record class in java 14
		//*******
		var d = dslContext.select(
				Tables.CUSTOMERS.CUSTOMER_ID,
				Tables.CUSTOMERS.FIRST_NAME,
				select(
				groupConcat(Tables.BOOKS.NUMBER_OF_PAGES).as("bibi"))
				.from(Tables.BOOKS)
				.where(Tables.BOOKS.CUSTOMER_ID.eq(Tables.CUSTOMERS.CUSTOMER_ID)).asField()
				)
			   .from(Tables.CUSTOMERS)
			   .fetch();
		
		System.out.println(d);
		
		 return null;
	}
	
	/*this method directly convert select result to be called directly by REST controller no need to Model object that will again be converted to JSON
	* we need to call formatJSON() after fetching the result
	*/
	public Collection<Customer> getAllCustomersJson() {
		
		//*******
		// var keyword was introduced in java 10
		// record class in java 14
		//*******
		var d = dslContext.select(
				Tables.CUSTOMERS.CUSTOMER_ID,
				Tables.CUSTOMERS.FIRST_NAME,
				select(
				groupConcat(Tables.BOOKS.NUMBER_OF_PAGES).as("bibi"))
				.from(Tables.BOOKS)
				.where(Tables.BOOKS.CUSTOMER_ID.eq(Tables.CUSTOMERS.CUSTOMER_ID)).asField()
				)
			   .from(Tables.CUSTOMERS)
			   .fetch().formatJSON();
		
		System.out.println(d);
		
		 return null;
	}
	
//	public Collection<Customer> getAllCustomers() {
//		 //return dslContext.selectFrom(Tables.CUSTOMERS).fetchInto(Customer.class);
////		Result<Record3<Long,String,Result<Record2<Long,Long>>>> d =
//		Result<Record3<Long,String, Long>> d= 
//		dslContext.select(
//				Tables.CUSTOMERS.CUSTOMER_ID,
//				Tables.CUSTOMERS.FIRST_NAME,
//				Tables.BOOKS.NUMBER_OF_PAGES
//				)
//			   .from(Tables.CUSTOMERS)
//			   .join(Tables.BOOKS).on(Tables.BOOKS.CUSTOMER_ID.eq(Tables.CUSTOMERS.CUSTOMER_ID))
//			   .fetch();
////		System.out.println(d.toString());
//		 return null;
//	}

	
	@Override
	public Set<Book> findCustomerBooks(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
