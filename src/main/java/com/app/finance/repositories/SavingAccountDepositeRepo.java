package com.app.finance.repositories;

import org.springframework.data.repository.CrudRepository;

import com.app.finance.entity.SavingAccDepositeDtls;

public interface SavingAccountDepositeRepo extends CrudRepository<SavingAccDepositeDtls, Long> {

}
