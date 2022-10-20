package com.wv.repositories;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wv.model.Book;
import com.wv.model.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class CustomerRepoJdbcImpl implements ICustomerRepo{

	/*
	 * this repository will be discovered automatically because it is in child package of where @SpringBootApplication exists (i.e com.wv). 
	 * */
	// Spring Boot will create and configure DataSource and JdbcTemplate based on application.properties
	// To use it, just @Autowired
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public void save(Customer customer) {
//		log.info("start saving customer" + customer.getCustomerId());
		// TODO Auto-generated method stub
	}

	public int count() {
		return jdbcTemplate.queryForObject("select count(*) from customers", Integer.class);
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
	public Set<Book> findCustomerBooks(Integer customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
