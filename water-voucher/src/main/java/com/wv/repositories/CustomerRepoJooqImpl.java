package com.wv.repositories;
import static org.jooq.impl.DSL.groupConcat;
import static org.jooq.impl.DSL.select;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//import org.jooq.DSLContext;
//import org.jooq.Records;
import org.jooq.DSLContext;
import org.jooq.Records;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wv.jooq.model.Tables;
import com.wv.model.Book;
import com.wv.model.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class CustomerRepoJooqImpl implements CustomerRepo{

	@Autowired
    private DSLContext dslContext;
	
	@Override
	public void save(Customer customer) {
		
		log.info("start saving customers using jooq");
		Long customerId = dslContext.insertInto(Tables.CUSTOMERS, 
			Tables.CUSTOMERS.CUSTOMER_ID, Tables.CUSTOMERS.FIRST_NAME, Tables.CUSTOMERS.LAST_NAME, Tables.CUSTOMERS.PHONE_NO, Tables.CUSTOMERS.CREATED_AT, Tables.CUSTOMERS.CREATE_USER_ID, Tables.CUSTOMERS.MODIFIED_AT, Tables.CUSTOMERS.MODIFY_USER_ID)
			.values(customer.getCustomerId(), customer.getFirstName(), customer.getLastName(), customer.getPhoneNo(),customer.getCreatedAt(), customer.getCreateUserId(), customer.getModifiedAt(), customer.getModifyUserId())
			.returningResult(Tables.CUSTOMERS.CUSTOMER_ID)
			.fetchOne()
			.component1();
//			.returningResult(Tables.CUSTOMERS.CUSTOMER_ID, Tables.CUSTOMERS.FIRST_NAME, Tables.CUSTOMERS.LAST_NAME, Tables.CUSTOMERS.PHONE_NO, Tables.CUSTOMERS.CREATED_AT, Tables.CUSTOMERS.CREATE_USER_ID, Tables.CUSTOMERS.MODIFIED_AT, Tables.CUSTOMERS.MODIFY_USER_ID)
//			.fetchSingleInto(Customer.class); this will return a customer object with the inserted ID, but if DB doesnot support return @@identity, JOOQ will issue new select which may bring another newly inserted record by different thread, so better not to use methods with unexpected behaviors
		
		customer.setCustomerId(customerId);

	}
	
	@Override
	public int count() {
		log.info("start counting customers using jooq");
		return dslContext.fetchCount(Tables.CUSTOMERS);
	}

	@Override
	public Optional<Customer> get(Long id) {
		Customer customer = 
		dslContext.select(
				Tables.CUSTOMERS.CUSTOMER_ID
				,Tables.CUSTOMERS.FIRST_NAME
				,Tables.CUSTOMERS.LAST_NAME
				,Tables.CUSTOMERS.PHONE_NO
				,Tables.CUSTOMERS.CREATED_AT
				,Tables.CUSTOMERS.MODIFIED_AT
				,Tables.CUSTOMERS.CREATE_USER_ID
				,Tables.CUSTOMERS.MODIFY_USER_ID
			,select(Tables.BOOKS.BOOK_ID, Tables.BOOKS.NUMBER_OF_PAGES)
			.from(Tables.BOOKS)
			.where(Tables.BOOKS.CUSTOMER_ID.eq(Tables.CUSTOMERS.CUSTOMER_ID))
			.asMultiset()
			//method 1: if we need to select less than the full object, then we should not use construct, because in order for the mapper to work we should have only one constructor in Book that match the selected fields by number and type, as good practice the constructor should be for all class members to mimic the behavior of JPA entity beans, anything less than that we should use custom methods and lambda like method 2
//			.convertFrom(r -> r.map(Records.mapping(Book::new)))
			//method 2: using lambda and a method to set bookId and pageNumbers, because method reference for constructor will not work if Book class has more than one constructor, that's why i created setBookOfCustomer()
			.convertFrom(r -> r.map(t->new Book().setBookOfCustomer(t.component1(),t.component2())))
		)
	   .from(Tables.CUSTOMERS)
	   .where(Tables.CUSTOMERS.CUSTOMER_ID.eq(id))
	   .fetchSingleInto(Customer.class);
	 
		return Optional.of(customer);
	}

	@Override
	public Collection<Customer> getAll() {
		 return dslContext.selectFrom(Tables.CUSTOMERS).fetchInto(Customer.class);
	}
	
	
	public Collection<Customer> getAllWithBooks() {
		
		List<Customer> customerList =
				dslContext.select(
						Tables.CUSTOMERS.CUSTOMER_ID
						,Tables.CUSTOMERS.FIRST_NAME
						,Tables.CUSTOMERS.LAST_NAME
						,Tables.CUSTOMERS.PHONE_NO
						,Tables.CUSTOMERS.CREATED_AT
						,Tables.CUSTOMERS.MODIFIED_AT
						,Tables.CUSTOMERS.CREATE_USER_ID
						,Tables.CUSTOMERS.MODIFY_USER_ID
					,select(Tables.BOOKS.BOOK_ID, Tables.BOOKS.NUMBER_OF_PAGES)
					.from(Tables.BOOKS)
					.where(Tables.BOOKS.CUSTOMER_ID.eq(Tables.CUSTOMERS.CUSTOMER_ID))
					.asMultiset()
					//method 1: if we need to select less than the full object, then we should not use construct, because in order for the mapper to work we should have only one constructor in Book that match the selected fields by number and type, as good practice the constructor should be for all class members to mimic the behavior of JPA entity beans, anything less than that we should use custom methods and lambda like method 2
//					.convertFrom(r -> r.map(Records.mapping(Book::new)))
					//method 2: using lambda and a method to set bookId and pageNumbers, because method reference for constructor will not work if Book class has more than one constructor, that's why i created setBookOfCustomer()
					.convertFrom(r -> r.map(t->new Book().setBookOfCustomer(t.component1(),t.component2())))
				)
			   .from(Tables.CUSTOMERS)
			   //using method reference
			   .fetch(Records.mapping(Customer::new));
			   //using lambda
//			   .fetch(currentRecord->new Customer(currentRecord.component1(), currentRecord.component2(), currentRecord.component3(), currentRecord.component4(), currentRecord.component5(), currentRecord.component6()));
			   //using old way
//			   .fetch(new RecordMapper<Record4<Long, String, String, List<Book>>,Customer>(){
//				   @Override
//				   public Customer map(Record4<Long, String, String, List<Book>> currentRecord) {
//					   return new Customer(currentRecord.component1(), currentRecord.component2(), currentRecord.component3(), currentRecord.component4());
//				   }
//			   });
		
		
		return customerList;
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

	@Override
	public Set<Book> findCustomerBooks(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
