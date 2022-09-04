package com.wv.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class Customer {

	private Long customerId;
	private String firstName;
	private String lastName;
	private Long phoneNo;
	private List<Address> addressesList;
	//private List<Order> ordersList;
	private List<Book> booksList;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt; //(changed automatically on every record change) will be used for optimistic locking
	private String createUserId;
	private String modifyUserId;
}
