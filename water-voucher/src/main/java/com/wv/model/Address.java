package com.wv.model;
import java.time.LocalDate;

import org.jooq.Record;

import com.wv.jooq.model.Tables;
import com.wv.jooq.model.tables.pojos.Customers;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class Address {

	private Long addressId;
	private Customer customer;
	private Long zoneNo;
	private Long streetNo;
	private Long buildingNo;
	private String addressDesc;
	private LocalDate createdAt;
	private String createdBy;
	private LocalDate modifiedAt; //(changed automatically on every record change) will be used for optimistic locking
	private String modifiedBy;
	
	public Address setRecord(Record record) {
		
		this.addressId = record.indexOf(Tables.ADDRESSES.ADDRESS_ID) != -1 ? record.get(Tables.ADDRESSES.ADDRESS_ID): null;
		this.customer = record.indexOf("customers") != -1 ? Customer.setCustomer((Customers)record.get("customers")): null;
		this.zoneNo = record.indexOf(Tables.ADDRESSES.ZONE_NO) != -1 ? record.get(Tables.ADDRESSES.ZONE_NO): null;
		
		return this;
	}
}
