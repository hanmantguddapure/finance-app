package com.app.finance.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.app.finance.entity.PersonalLoan;
import com.app.finance.entity.PersonalLoanInstallmentsDtls;

public interface PersonalLoanInstallmentRepo extends CrudRepository<PersonalLoanInstallmentsDtls, Long> {
	public List<PersonalLoanInstallmentsDtls> findByPersonalLoanAccountNo(PersonalLoan personalLoanAccountNo);

}
