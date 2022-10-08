package com.wv.repositories;

import java.util.Set;

import com.wv.model.Book;
import com.wv.model.Customer;

public interface CustomerRepo extends GlobalRepo<Customer>{

	public Set<Book> findCustomerBooks(Integer customerId);
}
