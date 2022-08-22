package com.wv.model;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class Address {

	private Long addressId;
	private Long zoneNo;
	private Long streetNo;
	private Long buildingNo;
	private String addressDesc;
}
