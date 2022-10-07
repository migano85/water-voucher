package com.wv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wv.repositories.CustomerRepoJdbcImpl;
import com.wv.repositories.CustomerRepoJooqImpl;

@SpringBootApplication
//@ComponentScan("ANOTHER base package") if we want to scan for components in different base package
public class WaterVoucherApplication implements CommandLineRunner{

	private static final Logger log = LoggerFactory.getLogger(WaterVoucherApplication.class);
	
	@Autowired
	private CustomerRepoJdbcImpl customerRepoJdbcImpl;
	
	@Autowired
	private CustomerRepoJooqImpl customerRepoJooqImpl;
	
	public static void main(String[] args) {
		SpringApplication.run(WaterVoucherApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		log.info("***********");
//		log.info(customerRepoJdbcImpl.count() + "");
//		log.info("***********");
		log.info("***********");
		log.info(customerRepoJooqImpl.count() + "");
		log.info("***********");
		log.info(customerRepoJooqImpl.getAllWithBooks() + "");
		log.info("&&&&&&&&&&&&&&&&&&&&&");
//		log.info(customerRepoJooqImpl.get(1L) + "");
		log.info("***********");
	}

}
