package com.app.finance.repositories;

import org.springframework.data.repository.CrudRepository;

import com.app.finance.entity.PersonalLoan;

public interface PersonalLoanRepo extends CrudRepository<PersonalLoan, Long> {

}
