package com.app.finance.repositories;

import org.springframework.data.repository.CrudRepository;

import com.app.finance.entity.LkpType;

public interface LkpTypeRepo extends CrudRepository<LkpType, Long> {
	public LkpType findByLkpTypeName(String lkpTypeName);
}
