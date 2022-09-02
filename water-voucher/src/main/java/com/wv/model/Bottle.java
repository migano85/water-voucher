package com.wv.model;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class Bottle {

	/******************
	* as the case of book-voucher aggregate, bottle never exist outside the contest of voucher
	* so voucher will be aggregate root and bottle will not have mapping to voucher (one-directional relationship)
	******************
	*/
	private Long bottleId;
	private Double size; //in little
	private String serialNo;
	private boolean filled;
	//private List<OrderBottle> bottleOrdersList; //(History:maximum 1 has deliveryDate=null)
	private Date createdAt;
	private Date modifiedAt; //(changed automatically on every record change) will be used for optimistic locking
	private Long createUserId;
	private Long modifyUserId;
}

