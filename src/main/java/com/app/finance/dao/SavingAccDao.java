package com.app.finance.dao;

import com.app.finance.entity.SavingAccDepositeDtls;
import com.app.finance.entity.SavingAccount;

public interface SavingAccDao {
	public SavingAccount saveOrUpdate(SavingAccount savingAccount);

	public SavingAccDepositeDtls saveOrUpdate(SavingAccDepositeDtls savingDepositeDtls);
}
