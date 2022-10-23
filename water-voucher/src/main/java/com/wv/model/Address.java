package com.wv.model;
import org.jooq.Record;

import com.wv.jooq.model.Tables;
import com.wv.jooq.model.tables.pojos.Addresses;
import com.wv.jooq.model.tables.pojos.Customers;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
@ToString(doNotUseGetters = true, callSuper = true)
public class Address extends Addresses{

	private static final long serialVersionUID = 1L;
	
	private Customer customer;
	
	public Address setRecord(Record record) {
		
		setAddressId(record.indexOf(Tables.ADDRESSES.ADDRESS_ID) != -1 ? record.get(Tables.ADDRESSES.ADDRESS_ID): null);
		this.customer = record.indexOf("customers") != -1 ? Customer.setCustomer((Customers)record.get("customers")): null;
		setZoneNo(record.indexOf(Tables.ADDRESSES.ZONE_NO) != -1 ? record.get(Tables.ADDRESSES.ZONE_NO): null);
		
		return this;
	}
}
