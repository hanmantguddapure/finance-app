package com.app.finance.dao;

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
	public SavingAccDepositeDtls saveOrUpdate(SavingAccDepositeDtls savingDepositeDtls) {
		return savingAccountDepositeRepo.save(savingDepositeDtls);
	}

}
