package com.wv.repositories;

import java.util.List;

import com.wv.model.Book;
import com.wv.model.Voucher;

public interface IBookRepo extends IGlobalRepo<Book>{

	public void fillBookVouchers(Long bookId, List<Voucher> vouchersList);
	public void deleteVoucher(Long bookId, Long voucherId);//to delete voucher we need bookId which is the aggregate root, we cannot do anything to the aggregate without aggregate root Id
}
