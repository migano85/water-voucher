package com.wv.repositories;

import static org.jooq.impl.DSL.select;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Records;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wv.jooq.model.Tables;
import com.wv.model.Address;
import com.wv.model.Book;
import com.wv.model.Customer;

@Repository
public class CustomerRepoJooqImpl implements ICustomerRepo {

	/*
	 * IMPORTANT NOTES ABOUT JOOQ
	 * --------------------------
	 * 
	 * org.jooq.Result<Record> is equivalent to List<Record> because org.jooq.Result
	 * extends java.util.List
	 * Record1 or Record2 or ... Record100 all extends Record, this is useful for
	 * mass manipulation.
	 * 
	 */
	@Autowired
	private DSLContext dslContext;

	@Override
	public void save(Customer customer) {

		// Record1<Integer> rec =
		Long customerId = dslContext.insertInto(Tables.CUSTOMERS,
				Tables.CUSTOMERS.FIRST_NAME, Tables.CUSTOMERS.LAST_NAME, Tables.CUSTOMERS.PHONE_NO,
				Tables.CUSTOMERS.CREATED_AT, Tables.CUSTOMERS.CREATED_BY, Tables.CUSTOMERS.MODIFIED_AT,
				Tables.CUSTOMERS.MODIFIED_BY)
				.values(customer.getFirstName(), customer.getLastName(), customer.getPhoneNo(), customer.getCreatedAt(),
						customer.getCreatedBy(), customer.getModifiedAt(), customer.getModifiedBy())
				.returningResult(Tables.CUSTOMERS.CUSTOMER_ID)
				.fetchOne()
				.component1();
		// .returningResult(Tables.CUSTOMERS.CUSTOMER_ID, Tables.CUSTOMERS.LAST_NAME)
		// .fetch();
		// .returningResult(Tables.CUSTOMERS.CUSTOMER_ID, Tables.CUSTOMERS.FIRST_NAME,
		// Tables.CUSTOMERS.LAST_NAME, Tables.CUSTOMERS.PHONE_NO,
		// Tables.CUSTOMERS.CREATED_AT, Tables.CUSTOMERS.CREATE_USER_ID,
		// Tables.CUSTOMERS.MODIFIED_AT, Tables.CUSTOMERS.MODIFY_USER_ID)
		// .fetchSingleInto(Customer.class); this will return a customer object with the
		// inserted ID, but if DB does not support return @@identity, JOOQ will issue
		// new select which may bring another newly inserted record by different thread,
		// so better not to use methods with unexpected behaviors

		// customer.setCustomerId(rec != null ?rec.component1() : null);

		customer.setCustomerId(customerId);
	}

	public void persist(Customer customer) {

		Long customerId = null;
		dslContext.insertInto(Tables.CUSTOMERS,
				Tables.CUSTOMERS.CUSTOMER_ID, Tables.CUSTOMERS.FIRST_NAME, Tables.CUSTOMERS.LAST_NAME,
				Tables.CUSTOMERS.PHONE_NO,
				Tables.CUSTOMERS.CREATED_AT, Tables.CUSTOMERS.CREATED_BY, Tables.CUSTOMERS.MODIFIED_AT,
				Tables.CUSTOMERS.MODIFIED_BY)
				.values(customer.getCustomerId(), customer.getFirstName(), customer.getLastName(),
						customer.getPhoneNo(), customer.getCreatedAt(),
						customer.getCreatedBy(), customer.getModifiedAt(), customer.getModifiedBy())
				.execute();
		// .onDuplicateKeyUpdate()
		// .set(Tables.CUSTOMERS.FIRST_NAME, customer.getFirstName())
		// .set(Tables.CUSTOMERS.LAST_NAME, customer.getLastName())
		// .returningResult(Tables.CUSTOMERS.CUSTOMER_ID)
		// .fetchOne()
		// .getValue(Tables.CUSTOMERS.CUSTOMER_ID);// I can use .component1(); instead

		customer.setCustomerId(customerId);
	}

	@Override
	public void update(Customer customer) {

		dslContext.update(Tables.CUSTOMERS)
				.set(Tables.CUSTOMERS.FIRST_NAME, customer.getFirstName())
				.set(Tables.CUSTOMERS.LAST_NAME, customer.getLastName())
				.where(Tables.CUSTOMERS.CUSTOMER_ID.eq(customer.getCustomerId()))
				.execute();
	}

	public int count() {
		return dslContext.fetchCount(Tables.CUSTOMERS);
	}

	@Override
	public Optional<Customer> get(Long id) {
		Customer customer = dslContext.select(
				Tables.CUSTOMERS.CUSTOMER_ID, Tables.CUSTOMERS.FIRST_NAME, Tables.CUSTOMERS.LAST_NAME,
				Tables.CUSTOMERS.PHONE_NO, Tables.CUSTOMERS.CREATED_AT, Tables.CUSTOMERS.MODIFIED_AT,
				Tables.CUSTOMERS.CREATED_BY, Tables.CUSTOMERS.MODIFIED_BY,
				select(Tables.BOOKS.BOOK_ID, Tables.BOOKS.NUMBER_OF_PAGES)
						.from(Tables.BOOKS)
						.where(Tables.BOOKS.CUSTOMER_ID.eq(Tables.CUSTOMERS.CUSTOMER_ID))
						.asMultiset()
						// method 1: if we need to select less than the full object, then we should not
						// use construct, because in order for the mapper to work we should have only
						// one constructor in Book that match the selected fields by number and type, as
						// good practice the constructor should be for all class members to mimic the
						// behavior of JPA entity beans, anything less than that we should use custom
						// methods and lambda like method 2
						// .convertFrom(r -> r.map(Records.mapping(Book::new)))

						// method 2: using lambda to implement RecordMapper<Record, Book> (it takes
						// record and return book) because method reference for constructor will not
						// work if Book class has more than one constructor, that's why i created
						// setBook()
						.convertFrom(r -> r.map(/* new ArrayList<Book>( */rec -> new Book().setRecord(rec))) // if I

				// method 3: inline implementation of functional interface RecordMapper
				// .convertFrom(r->
				// new ArrayList<Book>( //if I want the result to be ArrayList<Book> instead of
				// List<book> just wrap the resulting List<book> inside ArrayList constructor
				// r.map(//map() takes RecordMapper and return List<book>
				// new RecordMapper<org.jooq.Record, Book>() {
				// @Override
				// public Book map(org.jooq.Record rec) {
				// Book book = new Book();
				// book.setBook(rec);
				// return book;
				// }
				// }
				//
				// )//map
				// )//arrayList<Book>
				// )//convertFrom

				,
				select(Tables.ADDRESSES.ADDRESS_ID, Tables.ADDRESSES.ZONE_NO, Tables.ADDRESSES.STREET_NO,
						Tables.ADDRESSES.BUILDING_NO)
						.from(Tables.ADDRESSES)
						.where(Tables.ADDRESSES.CUSTOMER_ID.eq(Tables.CUSTOMERS.CUSTOMER_ID))
						.asMultiset()
						.convertFrom(r -> r.map(/* new ArrayList<Book>( */rec -> new Address().setRecord(rec))))
				.from(Tables.CUSTOMERS)
				.where(Tables.CUSTOMERS.CUSTOMER_ID.eq(id))
				.fetchSingleInto(Customer.class);
		// .fetchInto(Records.mapping(Customer::setRecord));
		// .fetchInto(rec -> rec.map(Records.mapping(Customer::setRecord)));

		// RecordMapper<BookRecord, Book> r =new RecordMapper<BookRecord, Book>() {
		// @Override
		// public Book map(BookRecord rec) {
		// return new Book();
		// }
		// };

		return Optional.of(customer);
	}

	@Override
	public Collection<Customer> getAll() {
		return dslContext.selectFrom(Tables.CUSTOMERS).fetchInto(Customer.class);
	}

	@Override
	public void delete(Long id) {
		dslContext.delete(Tables.CUSTOMERS)
				.where(Tables.CUSTOMERS.CUSTOMER_ID.eq(id))
				.execute();
	}

	public Collection<Customer> getAllWithBooks() {

		List<Customer> customerList = dslContext.select(
				Tables.CUSTOMERS.CUSTOMER_ID, Tables.CUSTOMERS.FIRST_NAME, Tables.CUSTOMERS.LAST_NAME,
				Tables.CUSTOMERS.PHONE_NO, Tables.CUSTOMERS.CREATED_AT, Tables.CUSTOMERS.CREATED_BY,
				Tables.CUSTOMERS.MODIFIED_AT, Tables.CUSTOMERS.MODIFIED_BY,
				select(Tables.BOOKS.BOOK_ID, Tables.BOOKS.NUMBER_OF_PAGES)
						.from(Tables.BOOKS)
						.where(Tables.BOOKS.CUSTOMER_ID.eq(Tables.CUSTOMERS.CUSTOMER_ID))
						.asMultiset()
						// method 1: if we need to select less than the full object, then we should not
						// use construct, because in order for the mapper to work we should have only
						// one constructor in Book that match the selected fields by number and type, as
						// good practice the constructor should be for all class members to mimic the
						// behavior of JPA entity beans, anything less than that we should use custom
						// methods and lambda like method 2
						// .convertFrom(r -> r.map(Records.mapping(Book::new)))
						// method 2: using lambda and a method to set bookId and pageNumbers, because
						// method reference for constructor will not work if Book class has more than
						// one constructor, that's why i created setBookOfCustomer()
						.convertFrom(r -> r.map(rec -> new Book().setRecord(rec)))
		// ,select(Tables.)
		)
				.from(Tables.CUSTOMERS)
				// using method reference
				.fetch(Records.mapping(Customer::setWithBooks));
		// .fetch(Records.mapping(Customer::new));
		// using lambda
		// .fetch(currentRecord->new Customer(currentRecord.component1(),
		// currentRecord.component2(), currentRecord.component3(),
		// currentRecord.component4(), currentRecord.component5(),
		// currentRecord.component6()));
		// using old way
		// .fetch(new RecordMapper<Record4<Long, String, String,
		// List<Book>>,Customer>(){
		// @Override
		// public Customer map(Record4<Long, String, String, List<Book>> currentRecord)
		// {
		// return new Customer(currentRecord.component1(), currentRecord.component2(),
		// currentRecord.component3(), currentRecord.component4());
		// }
		// });

		return customerList;
	}
	/*
	 * this method directly convert select result to be called directly by REST
	 * controller no need to Model object that will again be converted to JSON
	 * we need to call formatJSON() after fetching the result
	 */

	@Override
	public List<Book> findCustomerBooks(Integer customerId) {

		return null;
	}

	@Override
	public List<Customer> searchCustomerByCriteria(Customer customer) {
		System.out.println("&&&&&&&&&&&&&&&&&");
		System.out.println(customer);
		ArrayList<Condition> conditions = new ArrayList<>();
		if (customer != null) {
			if (customer.getFirstName() != null && !customer.getFirstName().isBlank()) {
				conditions.add(Tables.CUSTOMERS.FIRST_NAME.equalIgnoreCase(customer.getFirstName()));
			}
			if (customer.getLastName() != null && !customer.getLastName().isBlank()) {
				conditions.add(Tables.CUSTOMERS.LAST_NAME.equalIgnoreCase(customer.getLastName()));
			}
		}

		return dslContext.selectFrom(Tables.CUSTOMERS)
				.where(conditions)
				.fetchInto(Customer.class);
	}

}
