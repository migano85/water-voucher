package com.wv.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wv.model.Book;
import com.wv.model.Voucher;
import com.wv.repositories.BookRepoJooqImpl;

@Service
public class BookService {

    @Autowired
	private BookRepoJooqImpl repoJooqImpl;
    
    public void createBookWithVouchers(Book book){
        repoJooqImpl.save(book);

        ArrayList<Voucher> vouchersList = new ArrayList<>();
        for(int i =0; i < book.getNumberOfPages(); i++){
            Voucher voucher = new Voucher();
            voucher.setVoucherSerial(createVoucherSerial());
            voucher.setBookId(book.getBookId());
            vouchersList.add(voucher);
        }

        repoJooqImpl.fillBookVouchers(book.getBookId(), vouchersList);
    }

    public static String createVoucherSerial(){
        return Calendar.getInstance().get(Calendar.YEAR) +  
         Calendar.getInstance().get(Calendar.MONTH) + 
         Calendar.getInstance().get(Calendar.DATE) + 
         Calendar.getInstance().getTimeInMillis() + 
         StringUtils.leftPad((new Random().nextInt(10000)+""), 5, "0");
    }
}
