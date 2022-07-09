package com.app.finance.repositories;

import org.springframework.data.repository.CrudRepository;

import com.app.finance.entity.LkpType;
import com.app.finance.entity.LkpValue;

public interface LkpValueRepo extends CrudRepository<LkpValue, Long> {
	public LkpValue findByLkpValueAndLkpTypeName(String lkpValueName, LkpType lkpTypeName);

}
