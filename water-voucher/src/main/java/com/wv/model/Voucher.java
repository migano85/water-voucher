package com.wv.model;

import com.wv.jooq.model.tables.pojos.Vouchers;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * Voucher will not have RestController, no one will insert vouchers in Book, they will be inserted automatically when book is created
 * which means book class needs Service class that will generate random serials that will be concatinated to book No and stored as voucher serials
 * user can only delete voucher inside a book
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(doNotUseGetters = true, callSuper = true)
public class Voucher extends Vouchers {

	private Bottle bottle;// one to one
	// private Book book; //voucher never exist outside a book, so it is part of an
	// aggregate where book is the aggregate root, so it will be one direction
	// relationship withBook having reference to voucher and not vice-versa

}
