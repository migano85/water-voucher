package com.wv.model;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class OrderBottle {

	//this is for bottle inside a single order
	private Long orderId;
	private Long bottleId;
	private String voucherNo;
	private Date deliveryDate;
	private Date bottleReturnDate;
	private Date createdAt;
	private Date modifiedAt; //(changed automatically on every record change) will be used for optimistic locking
	private Long createUserId;
	private Long modifyUserId;
}
