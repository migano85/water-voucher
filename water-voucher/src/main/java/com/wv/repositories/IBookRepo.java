package com.wv.repositories;

import java.util.List;

import com.wv.model.Book;
import com.wv.model.Voucher;

public interface IBookRepo extends IGlobalRepo<Book>{

	public void fillBookVouchers(Long bookId, List<Voucher> vouchersList);
	public void deleteBookVoucher(Long voucherId);
	public Voucher getBookVoucher(Long voucherId);
}
