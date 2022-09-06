package com.wv;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wv.model.Customer;
import com.wv.repositories.CustomerRepoJooqImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerService {

	@Autowired
	private CustomerRepoJooqImpl customerRepoJooqImpl;
	
	public Collection<Customer> getAllCustomers(){
	
		return null;
	}
}
