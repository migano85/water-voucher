package com.wv.model;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class Bottle {

	private Long bottleId;
	private Double size; //in little
	private Date createdAt;
	private Date modifiedAt; //(changed automatically on every record change) will be used for optimistic locking
	private Long createUserId;
	private Long modifyUserId;
}
