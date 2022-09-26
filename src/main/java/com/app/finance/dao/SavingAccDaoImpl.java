package com.app.finance.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.finance.entity.SavingAccDepositeDtls;
import com.app.finance.entity.SavingAccount;
import com.app.finance.repositories.SavingAccountDepositeRepo;
import com.app.finance.repositories.SavingAccountRepo;

@Repository
public class SavingAccDaoImpl implements SavingAccDao {
	@Autowired
	private SavingAccountRepo savingAccountRepo;
	@Autowired
	private SavingAccountDepositeRepo savingAccountDepositeRepo;

	@Override
	public SavingAccount saveOrUpdate(SavingAccount savingAccount) {
		return savingAccountRepo.save(savingAccount);
	}

	@Override
	public Optional<SavingAccount> findById(Long accountNumber) {
		return savingAccountRepo.findById(accountNumber);
	}

	public List<SavingAccount> findByIsActive(Short isActive) {
		return savingAccountRepo.findByIsActive(isActive);
	}

	@Override
	public SavingAccDepositeDtls saveOrUpdate(SavingAccDepositeDtls savingDepositeDtls) {
		return savingAccountDepositeRepo.save(savingDepositeDtls);
	}

	@Override
	public List<SavingAccDepositeDtls> findByAccountNumber(SavingAccount aoountNumber) {
		return savingAccountDepositeRepo.findByAccountNumber(aoountNumber);
	}
}
