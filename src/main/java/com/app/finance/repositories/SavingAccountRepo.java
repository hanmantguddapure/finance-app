package com.app.finance.repositories;

import org.springframework.data.repository.CrudRepository;

import com.app.finance.entity.SavingAccount;

public interface SavingAccountRepo extends CrudRepository<SavingAccount, Long> {

}
