package com.wv.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wv.model.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class CustomerRepoJdbcImpl implements CustomerRepo{

	/*
	 * this repository will be discovered automatically because it is in child package of where @SpringBootApplication exists (i.e com.wv). 
	 * */
	// Spring Boot will create and configure DataSource and JdbcTemplate based on application.properties
	// To use it, just @Autowired
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public int save(Customer customer) {
		log.info("start saving customer" + customer.getCustomerId());
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Long count() {
		log.info("start counting customers");
		return jdbcTemplate.queryForObject("select count(*) from customers", Long.class);
	}

}
