	package com.wv.model;


import java.util.Date;
import java.util.Set;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class Book {

	private Long bookId;
	private Long numberOfPages;
	Customer customer;
	private Set<Voucher> vouchersList;
	private Date createdAt;
	private Long createBy;
	private Date modifiedAt; //(changed automatically on every record change) will be used for optimistic locking
	private Long modifyBy;
}
