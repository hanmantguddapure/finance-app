package com.app.finance.dao;

import java.util.List;
import java.util.Optional;

import com.app.finance.entity.PersonalLoan;
import com.app.finance.entity.PersonalLoanInstallmentsDtls;

public interface PersonalLoanAccountDao {
	public PersonalLoan saveOrUpdate(PersonalLoan personalLoan);

	public Optional<PersonalLoan> findById(Long loanAccountNo);

	public PersonalLoanInstallmentsDtls saveOrUpdate(PersonalLoanInstallmentsDtls personalLoanInstallmentsDtls);

	public List<PersonalLoanInstallmentsDtls> findByLoanAccountNumber(PersonalLoan personalLoanAccountNo);
}
