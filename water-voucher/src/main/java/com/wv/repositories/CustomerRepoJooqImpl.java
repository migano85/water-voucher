package com.wv.repositories;

import java.sql.Date;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import org.jooq.DSLContext;
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
	public int save(Customer customer) {
		log.info("start saving customers using jooq");
		return dslContext.insertInto(Tables.CUSTOMERS, 
			Tables.CUSTOMERS.CUSTOMER_ID, Tables.CUSTOMERS.FIRST_NAME, Tables.CUSTOMERS.LAST_NAME, Tables.CUSTOMERS.PHONE_NO, Tables.CUSTOMERS.CREATED_AT, Tables.CUSTOMERS.CREATE_USER_ID, Tables.CUSTOMERS.MODIFIED_AT, Tables.CUSTOMERS.MODIFY_USERID)
			.values(customer.getCustomerId(), customer.getFirstName(), customer.getLastName(), customer.getPhoneNo(),customer.getCreatedAt(), customer.getCreateUserId(), customer.getModifiedAt(), customer.getModifyUserId())
			.execute() ;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Book> findCustomerBooks(Long customerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
