package com.wv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wv.repositories.CustomerRepoJdbcImpl;

@SpringBootApplication
public class WaterVoucherApplication implements CommandLineRunner{

	private static final Logger log = LoggerFactory.getLogger(WaterVoucherApplication.class);
	
	@Autowired
	private CustomerRepoJdbcImpl customerRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(WaterVoucherApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("***********");
		log.info(customerRepo.count() + "");
		log.info("***********");
		
	}

}
