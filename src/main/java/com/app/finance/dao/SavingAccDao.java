package com.app.finance.dao;

import java.util.List;
import java.util.Optional;

import com.app.finance.entity.SavingAccDepositeDtls;
import com.app.finance.entity.SavingAccount;

public interface SavingAccDao {
	public SavingAccount saveOrUpdate(SavingAccount savingAccount);

	public SavingAccDepositeDtls saveOrUpdate(SavingAccDepositeDtls savingDepositeDtls);

	public List<SavingAccDepositeDtls> findByAccountNumber(SavingAccount aoountNumber);

	Optional<SavingAccount> findById(Long accountNumber);

	public List<SavingAccount> findByIsActive(Short isActive);
}
