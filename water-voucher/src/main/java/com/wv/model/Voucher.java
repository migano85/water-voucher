package com.wv.model;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class Voucher {

	private Long voucherId;
	private Long voucherSerial;
	private Bottle bottle;//one to one
//	private Book book; //voucher never exist outside a book, so it is part of an aggregate where book is the aggregate root, so it will be one direction relationship withBook having reference to voucher and not vice-versa
	private Date createdAt;
	private Long createBy;
	private Date modifiedAt; //(changed automatically on every record change) will be used for optimistic locking
	private Long modifyBy;
}
