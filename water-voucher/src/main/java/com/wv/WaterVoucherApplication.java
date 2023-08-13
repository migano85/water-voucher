package com.wv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
// @ComponentScan("ANOTHER base package") if we want to scan for components in
// different base package
public class WaterVoucherApplication {

	// private static final Logger log =
	// LoggerFactory.getLogger(WaterVoucherApplication.class);

	// @Autowired
	// private CustomerRepoJooqImpl customerRepoJooqImpl;

	// @Autowired
	// private BookRepoJooqImpl bookRepoJooqImpl;

	// @Autowired
	// private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(WaterVoucherApplication.class, args);
	}

	/*
	 * the following corsGlobalConfiguerer() is a method that has a return type as
	 * NON functional interface, therefore the return of the method can be only an
	 * in-line interface implementation
	 * for better example check classWithMain.java and see public static
	 * Comparator<String> compMethod()
	 */

	// @Bean
	// public WebMvcConfigurer corsGlobalConfiguerer() {// this method is used to
	// overcome CORS security check

	// return new WebMvcConfigurer() {
	// @Override
	// public void addCorsMappings(CorsRegistry registry) {
	// registry.addMapping("/**").allowedOrigins("http://localhost:3000");
	// }
	// };
	// }
	/*
	 * the following printEnvVars() is a method that has a return type as functional
	 * interface, therefore the return of the method can be lambda
	 * for better example check classWithMain.java and see public static
	 * Comparator<String> compMethod()
	 */
	// @Bean
	// public CommandLineRunner printEnvVars() {

	// return args -> {
	// log.info("***********");
	// log.info(environment.getProperty("MYSQL_HOST"));
	// log.info("***********");
	// log.info(environment.getProperty("MYSQL_PASSWORD"));
	// log.info("***********");
	// log.info(environment.getProperty("MYSQL_PORT"));
	// log.info("***********");
	// log.info(environment.getProperty("MYSQL_URL"));
	// log.info("***********");
	// log.info(environment.getProperty("MYSQL_USER"));
	// // log.info(customerRepoJooqImpl.getAllWithBooks() + "");
	// //// log.info("***********");
	// // log.info(customerRepoJooqImpl.get(1L) +"");
	// // log.info("***********");
	// // bookRepoJooqImpl.getAll();
	// };
	// }

	// @Override
	// public void run(String... args) throws Exception {
	// log.info("***********");
	// log.info(environment.getProperty("MYSQL_HOST"));
	// log.info("***********");
	// log.info(environment.getProperty("MYSQL_PASSWORD"));
	// log.info("***********");
	// log.info(environment.getProperty("MYSQL_PORT"));
	// log.info("***********");
	// log.info(environment.getProperty("MYSQL_URL"));
	// log.info("***********");
	// log.info(environment.getProperty("MYSQL_USER"));
	//// log.info(customerRepoJooqImpl.getAllWithBooks() + "");
	////// log.info("***********");
	//// log.info(customerRepoJooqImpl.get(1L) +"");
	//// log.info("***********");
	//// bookRepoJooqImpl.getAll();
	// }

}
