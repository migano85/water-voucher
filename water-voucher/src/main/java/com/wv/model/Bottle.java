package com.wv.model;

import java.time.LocalDate;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class Bottle {

	private Long bottleId;
	private Double size; //in little
	private String serialNo;
	private boolean filled;
	private LocalDate createdAt;
	private String createdBy;
	private LocalDate modifiedAt; //(changed automatically on every record change) will be used for optimistic locking
	private String modifiedBy;
}

