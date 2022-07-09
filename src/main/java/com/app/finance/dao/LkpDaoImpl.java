package com.app.finance.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.finance.entity.LkpType;
import com.app.finance.entity.LkpValue;
import com.app.finance.repositories.LkpTypeRepo;
import com.app.finance.repositories.LkpValueRepo;

@Repository
public class LkpDaoImpl implements LkpDao {
	@Autowired
	LkpValueRepo lkpValueRepo;
	@Autowired
	LkpTypeRepo lkpTypeRepo;

	public LkpValue findByLkpValueAndLkpTypeName(String lkpValueName, LkpType lkpTypeName) {
		return lkpValueRepo.findByLkpValueAndLkpTypeName(lkpValueName, lkpTypeName);
	}

	public LkpType findByLkpTypeName(String lkpTypeName) {
		return lkpTypeRepo.findByLkpTypeName(lkpTypeName);
	}

}
