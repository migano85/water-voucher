package com.wv.model;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

/*
 * Voucher will not have RestController, no one will insert vouchers in Book, they will be inserted automatically when book is created
 * which means book class needs Service class that will generate random serials that will be concatinated to book No and stored as voucher serials
 * user can only delete voucher inside a book
 */
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
