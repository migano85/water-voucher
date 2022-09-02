package com.wv.repositories;

import com.wv.model.Customer;

public interface CustomerRepo {

	public int save(Customer customer);
	public Long count();
}
