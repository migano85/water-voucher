package com.wv.repositories;

import java.util.Collection;

import com.wv.model.Book;
import com.wv.model.Customer;

public interface ICustomerRepo extends IGlobalRepo<Customer>{

//	making return type as global as possible, let the implementation of multiple subclass be more flexible of using List or Set ...etc
	public Collection<Book> findCustomerBooks(Integer customerId);
	public Collection<Customer>searchCustomerByCriteria(Customer customer);
}
