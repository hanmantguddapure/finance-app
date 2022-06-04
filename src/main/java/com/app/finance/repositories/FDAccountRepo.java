package com.app.finance.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.app.finance.entity.CustDetail;
import com.app.finance.entity.FDAccount;

public interface FDAccountRepo extends CrudRepository<FDAccount, Long> {
	public List<FDAccount> findByIsActive(Byte isActive);

	public List<FDAccount> findByCustId(CustDetail custId);

}
