package com.wv.repositories;

import java.util.Set;

import com.wv.model.Book;
import com.wv.model.Customer;

public interface ICustomerRepo extends IGlobalRepo<Customer>{

	public Set<Book> findCustomerBooks(Integer customerId);
}
