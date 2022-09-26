package com.app.finance.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.app.finance.entity.SavingAccDepositeDtls;
import com.app.finance.entity.SavingAccount;

public interface SavingAccountDepositeRepo extends CrudRepository<SavingAccDepositeDtls, Long> {
	public List<SavingAccDepositeDtls> findByAccountNumber(SavingAccount aoountNumber);
}
