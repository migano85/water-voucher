package com.wv.model;
import java.util.Date;

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
	private Date createdAt;
	private Date modifiedAt; //(changed automatically on every record change) will be used for optimistic locking
	private Long createUserId;
	private Long modifyUserId;
}
