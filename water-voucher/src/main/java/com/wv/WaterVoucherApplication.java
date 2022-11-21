package com.wv;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.wv.jooq.model.tables.pojos.Customers;
import com.wv.repositories.BookRepoJooqImpl;
import com.wv.repositories.CustomerRepoJdbcImpl;
import com.wv.repositories.CustomerRepoJooqImpl;

@SpringBootApplication
//@ComponentScan("ANOTHER base package") if we want to scan for components in different base package
public class WaterVoucherApplication {

	private static final Logger log = LoggerFactory.getLogger(WaterVoucherApplication.class);
	
	@Autowired
	private CustomerRepoJooqImpl customerRepoJooqImpl;
	
	@Autowired
	private BookRepoJooqImpl bookRepoJooqImpl;
	
	@Autowired
	private Environment environment;
	
	public static void main(String[] args) {
		SpringApplication.run(WaterVoucherApplication.class, args);
	}
	
	/*
	 * the following printEnvVars() is a method that has a return type as functional interface, therefore the return of the method can be lambda
	 * for better example check classWithMain.java and see public static Comparator<String> compMethod()
	 */
	@Bean
	public CommandLineRunner printEnvVars() {
		
		return args -> {
			log.info("***********");
			log.info(environment.getProperty("MYSQL_HOST"));
			log.info("***********");
			log.info(environment.getProperty("MYSQL_PASSWORD"));
			log.info("***********");
			log.info(environment.getProperty("MYSQL_PORT"));
			log.info("***********");
			log.info(environment.getProperty("MYSQL_URL"));
			log.info("***********");
			log.info(environment.getProperty("MYSQL_USER"));
//			log.info(customerRepoJooqImpl.getAllWithBooks() + "");
////			log.info("***********");
//			log.info(customerRepoJooqImpl.get(1L) +"");
//			log.info("***********");
//			bookRepoJooqImpl.getAll();
		};
	}
	
	/*
	 * the following corsGlobalConfiguerer() is a method that has a return type as NON functional interface, therefore the return of the method can be only an in-line interface implementation
	 * for better example check classWithMain.java and see public static Comparator<String> compMethod()
	 */
	
	@Bean
	public WebMvcConfigurer corsGlobalConfiguerer(){//this method is used to overcome CORS security check

		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000");
			}
		};
	}

//	@Override
//	public void run(String... args) throws Exception {
//		log.info("***********");
//		log.info(environment.getProperty("MYSQL_HOST"));
//		log.info("***********");
//		log.info(environment.getProperty("MYSQL_PASSWORD"));
//		log.info("***********");
//		log.info(environment.getProperty("MYSQL_PORT"));
//		log.info("***********");
//		log.info(environment.getProperty("MYSQL_URL"));
//		log.info("***********");
//		log.info(environment.getProperty("MYSQL_USER"));
////		log.info(customerRepoJooqImpl.getAllWithBooks() + "");
//////		log.info("***********");
////		log.info(customerRepoJooqImpl.get(1L) +"");
////		log.info("***********");
////		bookRepoJooqImpl.getAll();
//	}
	

}
