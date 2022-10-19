package com.wv.repositories;

import java.util.Collection;
import java.util.Optional;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wv.jooq.model.Tables;
import com.wv.model.Address;

@Repository
public class AddressRepoJooqImpl implements IAddressRepo{

	@Autowired
    private DSLContext dslContext;
	
	@Override
	public void save(Address address) {
		Long customerId = address.getCustomer() != null? address.getCustomer().getCustomerId() : null;
		Long addressId = 
			dslContext.insertInto(Tables.ADDRESSES,
					Tables.ADDRESSES.CUSTOMER_ID, Tables.ADDRESSES.ZONE_NO, Tables.ADDRESSES.STREET_NO, Tables.ADDRESSES.BUILDING_NO, Tables.ADDRESSES.DESCRIPTION, Tables.ADDRESSES.CREATED_AT, Tables.ADDRESSES.CREATED_BY, Tables.ADDRESSES.MODIFIED_AT, Tables.ADDRESSES.MODIFIED_BY)
			.values(customerId, address.getZoneNo(), address.getStreetNo(), address.getBuildingNo(), address.getAddressDesc(), address.getCreatedAt(), address.getCreatedBy(), address.getModifiedAt(), address.getModifyBy())
			.returningResult(Tables.ADDRESSES.ADDRESS_ID)
			.fetchOne()
			.component1();
		
		address.setAddressId(addressId);
	}

	@Override
	public Optional<Address> get(Long id) {
		Address address = 
				dslContext.select(Tables.ADDRESSES)
				.from(Tables.ADDRESSES)
				.where(Tables.ADDRESSES.ADDRESS_ID.eq(id))
				.fetchOneInto(Address.class);
				
		return Optional.of(address);
	}

	@Override
	public Collection<Address> getAll() {
		
		Collection<Address> bookList = 
				dslContext.select(
						Tables.ADDRESSES.ADDRESS_ID
						,Tables.ADDRESSES.ZONE_NO
						,Tables.ADDRESSES.STREET_NO
						,Tables.ADDRESSES.BUILDING_NO
						,Tables.ADDRESSES.CREATED_AT
						,Tables.ADDRESSES.CREATED_BY
						,Tables.ADDRESSES.MODIFIED_AT
						,Tables.ADDRESSES.MODIFIED_BY
						,Tables.ADDRESSES.customers().mapping(com.wv.jooq.model.tables.pojos.Customers::new).as("customers")
				)
				.from(Tables.ADDRESSES)
				.fetch(r ->r.map(rec->new Address().setAddress(rec)));
		
		return bookList;
	}

}
