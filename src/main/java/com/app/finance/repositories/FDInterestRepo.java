package com.app.finance.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.app.finance.entity.FDAccount;
import com.app.finance.entity.FDInterest;

public interface FDInterestRepo extends CrudRepository<FDInterest, Long> {
	public List<FDInterest> findByFdAccountId(FDAccount fdAccountId);

	public List<FDInterest> findByFdAccountIdIn(List<FDAccount> fdAccountId);
}
