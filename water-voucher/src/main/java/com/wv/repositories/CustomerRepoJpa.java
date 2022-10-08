//package com.wv.repositories;
//
//import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//
//@RepositoryRestResource(path = "customers", collectionResourceRel = "customers")
//public interface CustomerRepoJpa extends PagingAndSortingRepository<Customer, Long>, IGlobalRepo {
//  pulbic customizedMethod(Customer);
//}

/*
 * this is to show I can access DB with multiple methods (JDBC, JPA, DATA-JDBC)
 * i just need to implements CustomerRepoJpa and add implementation for any customizedMethod.
 */