package com.wv.model;

import com.wv.jooq.model.tables.pojos.Bottles;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
@ToString(doNotUseGetters = true, callSuper = true)
public class Bottle extends Bottles {

	private static final long serialVersionUID = 1L;

}

