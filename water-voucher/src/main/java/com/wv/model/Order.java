package com.wv.model;


import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class Order {

	private Long orderId;
	private List<OrderBottle> orderBottlesList;
	private Date deliveryDate;
	private Date createdAt;
	private Date modifiedAt; //(changed automatically on every record change) will be used for optimistic locking
	private Long createUserId;
	private Long modifyUserId;
}
