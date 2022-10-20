package com.wv.repositories;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.wv.jooq.model.Tables;
import com.wv.model.Bottle;

public class BottleRepoJooqImpl implements IBottleRepo{

	@Autowired
    private DSLContext dslContext;
	
	@Override
	public void save(Bottle bottle) {
		
		Long bottleId = 
			dslContext.insertInto(Tables.BOTTLES,
					Tables.BOTTLES.SIZE, Tables.BOTTLES.SERIAL_NUMBER, Tables.BOTTLES.FILLED, Tables.ADDRESSES.CREATED_AT, Tables.ADDRESSES.CREATED_BY, Tables.ADDRESSES.MODIFIED_AT, Tables.ADDRESSES.MODIFIED_BY)
			.values(bottle.getSize(), bottle.getSerialNo(), bottle.isFilled(), bottle.getCreatedAt(), bottle.getCreatedBy(), bottle.getModifiedAt(), bottle.getModifiedBy())
			.returningResult(Tables.ADDRESSES.ADDRESS_ID)
			.fetchOne()
			.component1();
		
		bottle.setBottleId(bottleId);
		
	}

	@Override
	public Optional<Bottle> get(Long id) {
		Condition condition = Tables.BOTTLES.BOTTLE_ID.eq(id);
		
		Bottle bottle = 
				dslContext.select(Tables.BOTTLES)
				.from(Tables.BOTTLES)
				.where(condition)
				.fetchOneInto(Bottle.class);
		
		return Optional.of(bottle);
	}

	@Override
	public Collection<Bottle> getAll() {
		
		List<Bottle> bottleList = 
				dslContext.select(Tables.BOTTLES)
				.from(Tables.BOTTLES)
				.fetchInto(Bottle.class);
		
		return bottleList;
	}

	@Override
	public void delete(Long id) {
		dslContext.delete(Tables.BOTTLES)
			.where(Tables.BOTTLES.BOTTLE_ID.eq(id))
			.execute();
	}
}
